Ext.define('LinkFixer.controller.CenterPanelController',{
    extend: 'Ext.app.ViewController',

    alias:'controller.centerPanelController',


    afterRenderGrid: function(){
        this.getViewModel().getStore('RadioStore').reload();
        this.getViewModel().set('selectedButton', 'all');
        this.isLinkFinderWorking(this);
    },

    afterload: function () {
        debugger;
        var memoryStore = this.getViewModel().getStore('MemoryStore');
        var radioStore = this.getViewModel().getStore('RadioStore');

        memoryStore.getProxy().setData(radioStore.getRange());
        memoryStore.load();
    },

    startLinkFinder: function(){
        Ext.Ajax.request({
            url: '/findLinks',
            method:'POST',

            headers:{
                'Content-Type' : "application/json"
            },

            success: function(response, opts) {

            },

            failure: function(response, opts) {
                Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
            }
        });

        this.isLinkFinderWorking(this);
    },


    isLinkFinderWorking: function(controller){

        Ext.Ajax.request({
            url: '/isLinkFinderWorking',
            method:'POST',

            headers:{
                'Content-Type' : "application/json"
            },

            success: function(response, opts) {
                controller.getViewModel().set('isLinkFinderWorking', Ext.decode(response.responseText));
                if(controller.getViewModel().get('isLinkFinderWorking')){
                    controller.isLinkFinderWorking(controller);
                }
            },

            failure: function(response, opts) {
                Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
            }
        });
    },

    fixBrokenLinkOnlineRadioBox: function(grid, rowIndex, colIndex, item) {

        thisController = this;

        var sitenum;
        var rec = grid.getStore().getAt(rowIndex);

        if(item.iconCls == 'oicon'){
            sitenum = 'ONLINERADIO';
            if(!rec.get('ostream')){
                return ;
            }
        } else if(item.iconCls == 'ricon'){
            sitenum = 'RADIONET';
            if(!rec.get('rstream')){
                return ;
            }
        } else if(item.iconCls == 'cicon'){
            sitenum = 'CANLIRADYO';
            if(!rec.get('cstream')){
                return ;
            }
        }

        Ext.MessageBox.confirm(
            'Confirm', 'Emin misiniz?', callbackFunction);
        function callbackFunction(btn) {
            if(btn == 'yes') {

                Ext.Ajax.request({
                    url: '/updateFixedLink',
                    method:'POST',
                    jsonData:{'radioId':rec.get('id'), 'sitenum':sitenum},

                    maskConfig:{
                        target: grid
                    },

                    headers:{
                        'Content-Type' : "application/json"
                    },

                    success: function(response, opts) {
                        grid.unmask();
                        debugger;
                        if(Ext.decode(response.responseText)){
                            Ext.toast({html:'İşlem Başarılı', timeout: 2000, bodyCls: 'successBox'});
                            if(thisController.getViewModel().get('selectedButton') == 'all'){
                                thisController.getViewModel().getStore('RadioStore').load({url:'/listAllRadios'});
                            } else if(thisController.getViewModel().get('selectedButton') == 'diff'){
                                thisController.getViewModel().getStore('RadioStore').load({url:'/listAllLinkNull'});
                            }
                            thisController.getViewModel().getStore('MemoryStore').reload();
                        } else{
                            Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
                        }
                    },

                    failure: function(response, opts) {
                        grid.unmask();
                        Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
                    }
                });

            } else {

            }
        };
    },



    justCheck: function(grid, rowIndex, colIndex, item) {

        thisController = this;

        var rec = grid.getStore().getAt(rowIndex);


        if(!rec.get('linkCopied')) {
            Ext.MessageBox.confirm(
                'Confirm', 'Emin misiniz?', callbackFunction);

            function callbackFunction(btn) {
                if (btn == 'yes') {

                    Ext.Ajax.request({
                        url: '/justCheckFixed',
                        method: 'POST',
                        jsonData: {'radioId': rec.get('id')},

                        maskConfig: {
                            target: grid
                        },

                        headers: {
                            'Content-Type': "application/json"
                        },

                        success: function (response, opts) {
                            grid.unmask();
                            debugger;
                            if (Ext.decode(response.responseText)) {
                                Ext.toast({html: 'İşlem Başarılı', timeout: 2000, bodyCls: 'successBox'});
                                if (thisController.getViewModel().get('selectedButton') == 'all') {
                                    thisController.getViewModel().getStore('RadioStore').load({url: '/listAllRadios'});
                                } else if (thisController.getViewModel().get('selectedButton') == 'diff') {
                                    thisController.getViewModel().getStore('RadioStore').load({url: '/listAllLinkNull'});
                                }
                                thisController.getViewModel().getStore('MemoryStore').reload();
                            } else {
                                Ext.toast({html: 'Hata', timeout: 3000, bodyCls: 'failureBox'});
                            }
                        },

                        failure: function (response, opts) {
                            grid.unmask();
                            Ext.toast({html: 'Hata', timeout: 3000, bodyCls: 'failureBox'});
                        }
                    });

                } else {

                }
            };
        }
    },

    listAllRadios: function () {
        this.getViewModel().set('selectedButton', 'all');
        this.getViewModel().getStore('RadioStore').load({url:'/listAllRadios'});

    },

    listAllLinkNull: function () {
        this.getViewModel().set('selectedButton', 'diff');
        this.getViewModel().getStore('RadioStore').load({url:'/listAllLinkNull'});
    },


    openLinkWindow : function(grid, rowIndex, colIndex){

        var rec = grid.getStore().getAt(rowIndex);
        this.getViewModel().set('radioId', rec.get('id'));
        var win = this.getView().add({
            xtype:'window',
            title:'URL Giriniz',
            width:1000,
            reference:'linkWindow',
            modal:true,
            layout:'form',

            items:[
                {
                    xtype:'textfield',
                    name:'url',
                    allowBlank: false,
                    fieldlabel:'URL',
                    bind:{
                        value : '{url}'
                    }
                }
            ],
             buttons:[{
                text : 'Kaydet',
                 handler:'saveUrl'
             }]
        });

        win.show();
    },


    saveUrl: function() {

        thisController = this;

        var radioId = this.getViewModel().get('radioId');
        var url = this.getViewModel().get('url');

        Ext.Ajax.request({
            url: '/updateLinkOfRadio',
            method:'POST',
            jsonData:{'radioId':radioId, 'url': url},

            headers:{
                'Content-Type' : "application/json"
            },

            success: function(response, opts) {
                thisController.lookupReference('linkWindow').close();
                if(Ext.decode(response.responseText)){
                    Ext.toast({html:'İşlem Başarılı', timeout: 2000, bodyCls: 'successBox'});
                    if(thisController.getViewModel().get('selectedButton') == 'all'){
                        thisController.getViewModel().getStore('RadioStore').load({url:'/listAllRadios'});
                    } else if(thisController.getViewModel().get('selectedButton') == 'diff'){
                        thisController.getViewModel().getStore('RadioStore').load({url:'/listAllLinkNull'});
                    }
                } else{
                    Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
                }
            },

            failure: function(response, opts) {
                Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
            }
        });

    },

    resetLinksCopied: function() {

        thisController = this;

        Ext.Ajax.request({
            url: '/resetLinksCopied',
            method:'POST',

            headers:{
                'Content-Type' : "application/json"
            },

            success: function(response, opts) {
                if(Ext.decode(response.responseText)){
                    Ext.toast({html:'İşlem Başarılı', timeout: 2000, bodyCls: 'successBox'});
                    if(thisController.getViewModel().get('selectedButton') == 'all'){
                        thisController.getViewModel().getStore('RadioStore').load({url:'/listAllRadios'});
                    } else if(thisController.getViewModel().get('selectedButton') == 'diff'){
                        thisController.getViewModel().getStore('RadioStore').load({url:'/listAllLinkNull'});
                    }
                } else{
                    Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
                }
            },

            failure: function(response, opts) {
                Ext.toast({html:'Hata', timeout: 3000, bodyCls: 'failureBox'});
            }
        });

    }

})