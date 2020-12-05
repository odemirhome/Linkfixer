Ext.define('LinkFixer.store.MemoryStore',{
    extend:'Ext.data.Store',

    alias:'store.memorystore',

    model: 'LinkFixer.model.RadioModel',
    proxy: {
        type: 'memory',
        enablePaging: true,
        reader:{
            rootProperty:'resultObject',
            totalProperty:'total'
        }
    },
    pageSize: 10
});