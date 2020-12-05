Ext.define('LinkFixer.viewmodel.CenterPanelViewModel',{
    extend:'Ext.app.ViewModel',
    alias:'viewmodel.centerPanelViewModel',

    requires:[
        'LinkFixer.store.RadioStore',
        'LinkFixer.store.MemoryStore'
    ],

    stores:{
        RadioStore:{
            type:'radioStore',
            listeners: {
                load: 'afterload'
            }
        },

        MemoryStore:{
            type:'memorystore'
        }
    }

    ,

    data: {
        isLinkFinderWorking: false
    }
})