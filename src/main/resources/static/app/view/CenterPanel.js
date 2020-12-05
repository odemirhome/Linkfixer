Ext.define('LinkFixer.view.CenterPanel', {
    extend: 'Ext.grid.Panel',

    alias: 'widget.centerPanel',

    reference: 'fooGrid',

    requires:[
        'LinkFixer.viewmodel.CenterPanelViewModel',
        'LinkFixer.controller.CenterPanelController'
    ],

    viewConfig: {
        enableTextSelection: true
    },

    title:'Radyolar',

    controller:'centerPanelController',

    viewModel:{
        type:'centerPanelViewModel'
    },

    bind:{
        store:'{MemoryStore}'
    },

    viewConfig: {
        getRowClass: function(record, index) {
            var c = record.get('linkCopied');
            if (c == 1) {
                return 'positive';
            }
        }
    },

    columns:[
        {
            text : 'row',
            dataIndex: 'index',
            sortable : false,
            flex:1

        }, {
            xtype:'gridcolumn',
            dataIndex:'id',
            text:'Id',
            flex:1
        },{
            xtype:'gridcolumn',
            dataIndex:'name',
            text:'Name',
            flex:20
        },{
            xtype:'gridcolumn',
            dataIndex:'currentLink',
            text:'Bizdeki Link',
            flex:10,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank">Link</a>';
            }
        },{
            xtype:'gridcolumn',
            dataIndex:'currentImage',
            text:'Bizdeki Resim',
            flex:5,
            renderer: function(value){
                if(value)
                    return '<img style="height:50px; width:auto; max-width:500px;" src="' + value + '" />';
            },
        },{
            xtype:'gridcolumn',
            dataIndex:'ostream',
            text:'OnlineRadio Stream',
            flex:10,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank"><img width=\"16\" src="/images/oicon.png"></img>OnlineRadioBox</a>';
            }
        },{
            xtype:'gridcolumn',
            dataIndex:'oimg',
            text:'OnlineRadio Resim',
            flex:5,
            renderer: function(value){
                if(value)
                    return '<img style="height:50px; width:auto; max-width:500px;" src="http://' + value + '" />';
            },
        },{
            xtype:'gridcolumn',
            dataIndex:'ourl',
            text:'OnlineRadio Adres',
            flex:5,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank">SAYFA</a>';
            }
        },{
            xtype:'gridcolumn',
            dataIndex:'cstream',
            text:'CanlıRadio Stream',
            flex:10,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank"><img width="16" src="/images/cicon.png"></img>CanlıRadyo</a>';
            }
        },{
            xtype:'gridcolumn',
            dataIndex:'cimg',
            text:'CanliRadio Resim',
            flex:5,
            renderer: function(value){
                if(value)
                    return '<img style="height:50px; width:auto; max-width:500px;" src="' + value + '" />';
            },
        },{
            xtype:'gridcolumn',
            dataIndex:'curl',
            text:'CanlıRadio Adres',
            flex:5,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank">SAYFA</a>';
            }
        },{
            xtype:'gridcolumn',
            dataIndex:'rstream',
            text:'Radionet Stream',
            flex:10,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank"><img width="16" src="/images/ricon.png"></img>RadioNet</a>';
            }
        },{
            xtype:'gridcolumn',
            dataIndex:'rimg',
            text:'Radionet Resim',
            flex:5,
            renderer: function(value){
                if(value)
                    return '<img style="height:50px; width:auto; max-width:500px;" src="' + value + '" />';
            },
        },{
            xtype:'gridcolumn',
            dataIndex:'rurl',
            text:'Radionet Adres',
            flex:5,
            renderer: function (value) {
                if(value)
                    return '<a href="'+value+'" target="_blank">SAYFA</a>';
            }
        }, {
            xtype:'actioncolumn',
            width:120,
            items: [{
                    iconCls: 'oicon',
                    tooltip: 'OnlineRadioBox',
                    handler: 'fixBrokenLinkOnlineRadioBox'
                },{
                    iconCls: 'cicon',
                    tooltip: 'CanlıRadyoDinle',
                    handler: 'fixBrokenLinkOnlineRadioBox'
                },{
                    iconCls: 'ricon',
                    tooltip: 'RadioNet',
                    handler: 'fixBrokenLinkOnlineRadioBox'
                },{
                    iconCls: 'x-fa fa-link colorOrange',
                    tooltip: 'URL',
                    handler: 'openLinkWindow'
                },{
                    iconCls: 'x-fa fa-check colorGreen',
                    tooltip: 'Kontrol Edildi',
                    handler: 'justCheck'
                }
            ]
        }

    ],

    listeners:{
        afterrender:'afterRenderGrid'
    },


    dockedItems: [{
        xtype: 'toolbar',
        dock: 'top',

        items: [{
            xtype: 'segmentedbutton',
            items: [{
                text: 'Tüm Radyolar',
                pressed: true,
                handler: 'listAllRadios'
            }, {
                text: 'Linkleri Farklı Olan',
                handler: 'listAllLinkNull'
            }]
        }, {
            xtype:'label',
            html: '<img src="/images/spinner.gif" width="25" height="25">',
            bind: {
                visible:'{isLinkFinderWorking}'
            }
        }, {
            xtype:'button',
            handler: 'startLinkFinder',
            text:'Linkleri Bul'
        },{
            xtype:'button',
            handler:'resetLinksCopied',
            text : 'Kopyalananları Sıfırla'
        }]
    }],


    bbar: {
        xtype: 'pagingtoolbar',
        bind:{
            store:'{MemoryStore}'
        },
        displayInfo: true
    },


});