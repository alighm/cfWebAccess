define([
	'jquery',
	'underscore',
	'backbone',
	'text!templates/login.html',
	'views/app'
	], function($, _, Backbone, loginTemplate, AppView) {
	var LoginView = Backbone.View.extend({
		el: $("#main"),

		loginTemplate: _.template(loginTemplate),

		events: {
			"focus .login-form": "loginFocus",
			"blur .login-form": "loginBlur",
			"click .login-submit": "loginSubmit"
		},

		initialize: function() {
			this.render();

			this.email = $("#j_username");
			this.password = $("#j_password");
			this.signInUser = $("#signIn");
		},

		render: function() {
			$(this.el).html(this.loginTemplate({}));
		},

		loginFocus: function(ev) {
			var element = $(ev.currentTarget);

			element.removeClass("idleField").addClass("focusField");

			if (element[0].value == element[0].defaultValue) {
				element[0].value = '';
			}
			if (element[0].value != element[0].defaultValue) {
				element[0].select();
			}
		},

		loginBlur: function(ev) {
			var element = $(ev.currentTarget);

			element.removeClass("focusField").addClass("idleField");

			if ($.trim(element[0].value)  == '') {
				element[0].value = (element[0].defaultValue ? element[0].defaultValue : '');
			}
		},

		loginSubmit: function(ev) {
			var appView, self = this;

			$.post("j_security_check",
				{ j_username: this.email.val(), j_password: this.password.val() },
				function(data) {
					if (data) {
						$('.login').fadeOut('slow', function() {
							self.signInUser.html(data + " (<a href=\"logout\" >Sign Out</a>)");
							appView = new AppView;
							Ext.getBody().unmask();
						});
					} else {
						Ext.getBody().unmask();
						Ext.example.msg('UnAuthorized User', 'Please check your email address & password');
					}
				}, "json");
		}
	});

	return LoginView;
});