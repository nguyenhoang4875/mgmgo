$(document).ready(function () {
    fetch('/api/quote')
        .then(function (response) {
            return response.json();
        })
        .then(function (quote) {
            console.log(quote)
            $('#quote-content').html(quote.content);
            $('#quote-author').text(quote.title || "Bruce Lee");

            $('.quote-wrapper').removeClass('invisible');
        })
        .catch(function (error) {
            $('.quote-wrapper').removeClass('invisible');
        })
});