require.config({
	paths: {
		jquery: 'libs/jquery/jquery-min',
		underscore: 'libs/underscore/underscore',
		backbone: 'libs/backbone/backbone',
		text: 'libs/require/text'
	}
});

require(['views/login'], function(LoginView) {
	var loginView = new LoginView;
});