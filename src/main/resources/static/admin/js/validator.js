function Validator(options) {
	function getParent(element, selector) {
		while (element.parentElement) {
			if (element.parentElement.matches(selector)) {
				return element.parentElement;
			}
			element = element.parentElement;
		}
	}
	var selectorRules = {};
	function validate(inputElement, rule) {
		var errorMessage;
		var errorElement = getParent(inputElement, options.formGroupSelect)
				.querySelector(options.errorSelector);
		var rules = selectorRules[rule.selector];
		for (var i = 0; i < rules.length; ++i) {
			switch (inputElement.type) {
			case 'radio':

			case 'checkbox':
				errorMessage = rules[i](formElement.querySelector(rule.selector
						+ ':checked'));
				break;
			default:
				errorMessage = rules[i](inputElement.value);
			}

			if (errorMessage)
				break;
		}
		if (errorMessage) {
			errorElement.innerText = errorMessage;
			getParent(inputElement, options.formGroupSelect).classList
					.add('invalid');
		} else {
			errorElement.innerText = '';
			getParent(inputElement, options.formGroupSelect).classList
					.remove('invalid');
		}
		return !errorMessage;
	}
	var formElement = document.querySelector(options.form);
	if (formElement) {
		// Khi submit form
		formElement.onsubmit = function(e) {
			e.preventDefault();
			var isFormValid = true;
			options.rules.forEach(function(rule) {
				var inputElement = formElement.querySelector(rule.selector);
				var isValid = validate(inputElement, rule);
				if (!isValid) {
					isFormValid = false;
				}
			});
			if (isFormValid) {
				if (typeof options.onSubmit === 'function') {
					var enableInputs = formElement.querySelectorAll('[name]');
					var formValue = Array
							.from(enableInputs)
							.reduce(
									function(values, input) {
										switch (input.type) {
										case 'radio':
											values[input.name] = formElement
													.querySelector('input[name="'
															+ input.name
															+ '"]:checked').value;
											break;
										case 'checkbox':
											if (!input.matches(':checked')) {
												return values;
											}
											if (!Array
													.isArray(values[input.name])) {
												values[input.name] = [];
											}
											values[input.name]
													.push(input.value);
											break;
										case 'file':
											values[input.name] = input.files;
											break;

										default:
											values[input.name] = input.value;
										}
										return values;
									}, {});
					options.onSubmit(formValue);
				}
			}
		}
		// Lắng nghe sự kiện blur
		options.rules.forEach(function(rule) {

			// Lưu lại các rules cho mỗi input
			if (Array.isArray(selectorRules[rule.selector])) {
				selectorRules[rule.selector].push(rule.test);
			} else {
				selectorRules[rule.selector] = [ rule.test ];
			}

			var inputElements = formElement.querySelectorAll(rule.selector);
			Array.from(inputElements)
					.forEach(
							function(inputElement) {
								if (inputElement) {
									inputElement.onblur = function() {
										validate(inputElement, rule);
									}
								}
								inputElement.oninput = function() {
									var errorElement = getParent(inputElement,
											options.formGroupSelect)
											.querySelector(
													options.errorSelector);
									errorElement.innerText = '';
									getParent(inputElement,
											options.formGroupSelect).classList
											.remove('invalid');
								}
							});
		});
	}
}
Validator.isRequired = function(selector, message) {
	return {
		selector : selector,
		test : function(value) {
			return value ? undefined : message;
		}
	};
}
Validator.isEmail = function(selector, message) {
	return {
		selector : selector,
		test : function(value) {
			var regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
			return regex.test(value) ? undefined : message;
		}
	};
}
Validator.isMumber = function(selector, message) {
	return {
		selector : selector,
		test : function(value) {
			var regex = /[0-9]$/;
			return regex.test(value) ? undefined : message;
		}
	};
}
Validator.minLength = function(selector, min, message) {
	return {
		selector : selector,
		test : function(value) {
			return value.length >= min ? undefined : message;
		}
	};
}
Validator.maxValue = function(selector, max, message) {
	return {
		selector : selector,
		test : function(value) {
			return value < max ? undefined : message;
		}
	};
}
Validator.isConfirm = function(selector, getConfirmValue, message) {
	return {
		selector : selector,
		test : function(value) {
			return value === getConfirmValue() ? undefined : message;
		}
	};
}
Validator.isUsername = function(selector, message) {
	return {
		selector : selector,
		test : function(value) {
			var regex = /^[a-zA-Z0-9]+$/;
			return regex.test(value) ? undefined : message;
		}
	};
}
Validator.isPhone = function(selector, min, message) {
	return {
		selector : selector,
		test : function(value) {
			return value.length == min ? undefined : message;
		}
	};
}
Validator.isDateAfter = function(selector, getConfirmValue, message) {
	return {
		selector : selector,
		test : function(value) {
			var dateBegin = new Date(getConfirmValue());
			var dateEnd = new Date(value);
			var time1 = dateBegin.getTime();
			var time2 = dateEnd.getTime();
			if (time1 < time2) {
				return undefined;
			} else {
				return message;
			}
		}
	};
}
Validator.isFileImage = function(selector, message) {
	return {
		selector : selector,
		test : function(value) {
			if (value) {
				var allowedExtension = [ 'jpeg', 'jpg', 'png', 'gif', 'bmp' ];
				var fileExtension = value.split('.').pop().toLowerCase();
				var isValidFile = false;
				for ( var index in allowedExtension) {

					if (fileExtension === allowedExtension[index]) {
						isValidFile = true;
						break;
					}
				}

				if (!isValidFile) {
					return message;
				}
			}
			return undefined;
		}
	};
}
Validator.isFileExcel = function(selector, message) {
	return {
		selector : selector,
		test : function(value) {
			if (value) {
				var allowedExtension = [ '.xls', 'xlsx' ];
				var fileExtension = value.split('.').pop().toLowerCase();
				var isValidFile = false;
				for ( var index in allowedExtension) {

					if (fileExtension === allowedExtension[index]) {
						isValidFile = true;
						break;
					}
				}

				if (!isValidFile) {
					return message;
				}
			}

			return undefined;
		}
	};
}