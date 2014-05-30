function again(el, num) {
	num = num || 1;
	if (!el.again) {
		el.again = num;
	}
	if (el.again > 0) {
		el.again = this.again - 1;
		event.preventDefault();
	}
}
