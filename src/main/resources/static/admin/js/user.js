 $(document).ready(function() {
    	    $("#formAddSubmit").submit(function(evt) {
    	        evt.preventDefault();
    	        var data={};
    	  		var formData = $('#formAddSubmit').serializeArray();
    	  		$.each(formData, function(i, v) {
    	  			data["" + v.name + ""] = v.value;
    	  		});
    	       
    	        $.ajax({
    	            url: '/users',
    	            type: 'POST',
    	            contentType : "application/json",
    	            data: JSON.stringify(data),
    	            dataType : 'json',
    	            async: false,
    	            cache: false,
    	            success: function (response) {
    	            	window.location.href = "/admin_listUser";      
    	            },
    	            error: function (response) {
    	            	window.location.href = "/admin_index";
    	            }
    	        });
    	    });
      });