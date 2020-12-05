Ext.define('LinkFixer.model.RadioModel',{
    extend:'Ext.data.Model',

    fields:[
        {name:'id', type:'auto'},
        {name:'name', type:'string'},
        {name:'currentImage', type:'string'},
        {name:'currentLink', type:'string'},
        {name:'ostream', type:'string'},
        {name:'ourl', type:'string'},
        {name:'oimg', type:'string'},
        {name:'cstream', type:'string'},
        {name:'curl', type:'string'},
        {name:'cimg', type:'string'},
        {name:'rstream', type:'string'},
        {name:'rurl', type:'string'},
        {name:'rimg', type:'string'}
    ]
});