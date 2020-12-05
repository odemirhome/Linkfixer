Ext.application({
    name :'LinkFixer',

    requires:[
      'LinkFixer.view.MainPanel'
    ],
    launch: function(){
        Ext.create('LinkFixer.view.MainPanel',{
            renderTo:Ext.getBody()
        });
    }
});