Ext.define('LinkFixer.store.RadioStore', {
    extend:'Ext.data.Store',

    alias:'store.radioStore',
    model:'LinkFixer.model.RadioModel',
    autoLoad:false,

    remoteFilter: false,
    remoteSort: false,

    proxy:{
        type:'ajax',
        url:'/listAllRadios',
        actionMethods: {
            create:'POST',
            read:'POST',
            update:'POST',
            destroy:'POST'
        },
        paramsAsJson:true,
        reader:{
            type:'json',
            rootProperty:'resultObject',
            totalProperty:'total'
        }
    }
})