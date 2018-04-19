window.onload = function() {
    var validRegex = $('[ng-controller="dnaController"]').scope().validRegex;

    $('#dnaMatrix').on('keyup', '.matrix-input', function(e) {
        if (validRegex.match(e.key)) {
            let inputs = $('.matrix-input');
            let nextInput = inputs.get(inputs.index(this) + 1);
            if (nextInput) {
                nextInput.select();
            }
        }
    }).on('click', '.matrix-input', function() {
        this.select();
    });
};