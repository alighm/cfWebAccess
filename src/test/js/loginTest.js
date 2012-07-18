define(['views/login'], function(LoginView) {
	describe('LoginView Module', function() {
		var loginView = new LoginView;

		//Specs
		describe('LoginView.events', function() {
			it('defines these events', function() {
				var expected = {
					"focus .login-form": "loginFocus",
					"blur .login-form": "loginBlur",
					"click .login-submit": "loginSubmit"
				};
				expect(loginView.events).toEqual(expected);
			});
		});
	});
});