Ext.define('LinkFixer.view.MainPanel',{
    extend:'Ext.container.Viewport',
    alias:'widget.mainPanel',
    layout:'border',

    requires:[
        'LinkFixer.view.CenterPanel'
    ],

    items:[
        {
            xtype:'centerPanel',
            region:'center'
        }
    ]
})