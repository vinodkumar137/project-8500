const resend = document.querySelector('.resend');
function startTimer(duration, display) {
	var timer = duration,
		minutes,
		seconds;
	var intervalId = setInterval(function () {
		minutes = parseInt((timer / 60).toString(), 10);
		seconds = parseInt((timer % 60).toString(), 10);

		minutes = minutes < 10 ? '0' + minutes : minutes;
		seconds = seconds < 10 ? '0' + seconds : seconds;

		display.textContent = minutes + ':' + seconds;

		if (--timer < 0) {
			resend?.setAttribute('href', '/verify-signup');
			clearInterval(intervalId);
		}
	}, 1000);
}
startTimer(60, document.querySelector('#time'));
