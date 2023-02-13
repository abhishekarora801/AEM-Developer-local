/* global jQuery, Coral */
(function($, Coral) {
    "use strict";

    console.log(" --------CLIENTLIBS LOADED------- ");

    var registry = $(window).adaptTo("foundation-registry");

    // Validator for required for multifield max and min items
    registry.register("foundation.validation.validator", {
        selector: "[data-validation=geeks-multifield]",
        validate: function(element) {
            var el = $(element);
            let max = el.data("max-items");
            let min = el.data("min-items");
            let items = el.children("coral-multifield-item").length;
//            let domitems = el.children("coral-multifield-item");
            console.log("{} : {} :{} ",max,min,items);
            if(items>max){
                  /* If you use these, it will not allow the Add button to work */
//                domitems.last().remove();
                return `You can add only ${max} books. You are trying to add the ${items}th book.`;
            }
            if(items<min){
                return `Please add atleast ${min} books.`;
            }

        }
    });

    registry.register("foundation.validation.validator", {
        selector: "[data-validation=geeks-firstname-validation]",
        validate: function(element) {
            let el = $(element);
            let pattern = /[0-9a-z]/;
            let value = el.val();
            if(pattern.test(value)){
                return "Please add only Upper case letters in the name";
            }
        }
    });



   
})(jQuery, Coral);
