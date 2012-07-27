define([
	'jquery',
	'underscore',
	'backbone',
	'text!templates/app.html'
	], function($, _, Backbone, appTemplate) {
	var AppView = Backbone.View.extend({

		el: $("#main"),

		appTemplate: _.template(appTemplate),

		events: {
		},

		initialize: function() {
			this.render();

			// create the Data Store
			this.store = Ext.create('Ext.data.Store', {
				remoteSort: true,
				fields: ['name', 'resources.memory', 'instances', 'state', 'services', 'uris', 'staging.stack', 'staging.model'],
				proxy: {
					type: 'ajax',
					url: 'getApps',
					reader: {
						type: 'json',
						root: 'apps',
						totalProperty: 'totalCount'
					},

					// sends single sort as multi parameter
					simpleSortMode: true
				}
			});

			// editing cell plugin
			this.cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
				clicksToEdit: 1,
				listeners : {
					'edit': function(editor, e) {
						if (e.field == "instances") {
							Ext.getBody().mask("Modifying Instance...");
							$.get("updateInstance/" + e.record.get('name') + "/" + e.value, function() {
								e.grid.store.load();
								Ext.getBody().unmask();
							});
						}
					}
				}
			});

			this.grid = Ext.create('Ext.grid.Panel', {
				autoScroll: true,
				height: 300,
				forceFit: true,
				store: this.store,
				viewConfig: {
					loadMask: false
				},

				// grid columns
				columns:[{
					text: "Name",
					dataIndex: 'name',
					editor: {
						allowBlank: false
					},
					width: 150
				},{
					text: "Memory",
					dataIndex: 'resources.memory',
					width: 70,
					selectOnTab: true,
					editor: {
						xtype: 'combobox',
						allowBlank: false,
						selectOnTab: true,
						store: [
						['128M','128M'],
						['256M','256M'],
						['512M','512M'],
						['1G','1G'],
						['2G','2G']
						],
						lazyRender: true,
						listClass: 'x-combo-list-small'
					}
				},{
					text: "Instances",
					dataIndex: 'instances',
					width: 60,
					editor: {
						xtype: 'numberfield',
						selectOnTab: true,
						allowBlank: false,
						step: 1,
						minValue: 1,
						maxValue: 19
					}
				},{
					text: "Framework",
					dataIndex: "staging.model",
					width: 60,
					renderer: function(value) {
						if (!value.indexOf('node')) {
							return '<center><img src="resources/images/newNode-logo.png" /></center>';
						} else if (!value.indexOf('spring')) {
							return '<center><img src="resources/images/newSpring-logo.png" /></center>';
						} else if (!value.indexOf('grails')) {
							return '<center><img src="resources/images/newGrails-logo.png" /></center>';
						} else if (!value.indexOf('java')) {
							return '<center><img src="resources/images/java-logo.png" /></center>';
						} else if (!value.indexOf('sinatra')) {
							return '<center><img src="resources/images/newSinatra-logo.png" /></center>';
						}
					}
				},{
					text: "Stack",
					dataIndex: "staging.stack",
					width: 40,
					renderer: function(value) {
						if (!value.indexOf('ruby')) {
							return '<center><img src="resources/images/ruby-logo.png" /></center>';
						} else if (!value.indexOf('java')) {
							return '<center><img src="resources/images/java-logo.png" /></center>';
						} else if (!value.indexOf('node')) {
							return '<center><img src="resources/images/newNode-logo.png" /></center>';
						}
					}
				},{
					text: "State",
					dataIndex: 'state',
					width: 40,
					renderer: function(value) {
						return (value == 'STARTED') ? '<center><img src="resources/images/stop.png" /></center>':
							'<center><img src="resources/images/play.png" /></center>';
					},
					listeners : {
						'click' : function(t, el, rowIndex) {
							Ext.getBody().mask("Please wait...");
							var app = t.getStore().getAt(rowIndex);
							if ("STARTED" == app.get('state')) {
								$.get("stopApp/" + app.get('name'), function(data) {
									if (data.status) {
										t.store.load();
										Ext.getBody().unmask();
										Ext.example.msg('Success', data.message);
									} else {
										Ext.getBody().unmask();
										Ext.example.msg('Failed', data.message);
									}
								});
							} else {
								$.get("startApp/" + app.get('name'), function(data) {
									if (data.status) {
										t.store.load();
										Ext.getBody().unmask();
										Ext.example.msg('Success', data.message);
									} else {
										Ext.getBody().unmask();
										Ext.example.msg('Failed', data.message);
									}

								});
							}
						}
					}

				},{
					text: "Services",
					dataIndex: "services",
					width: 120,
					renderer: function(value) {
						var i, services = "";
						if (value == 'NoService') {
							return "No Service Bound";
						} else {
							for (i = 0; i < value.length; i++) {
								services += '<img src="resources/images/' + value[i].split(":")[1] + '-logo.png" />&nbsp;&nbsp;&nbsp;';
							}
							return services;
						}
					}
				},{
					text: "URLs",
					dataIndex: 'uris',
					width: 200
				}],
				renderTo: 'grid',
				plugins: [this.cellEditing]
			});
			this.store.load();
		},

//		stopApp: function(appName) {
//			console.log("STOPPING " + app.get('name'));
//		},

//		startApp: function(appName) {
//			console.log("STARTING" + app.get('name'));
//		},

		render: function() {
			$(this.el).html(this.appTemplate({}));
		}
	});

	return AppView;
});