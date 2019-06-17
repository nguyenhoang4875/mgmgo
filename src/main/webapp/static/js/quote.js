$(document).ready(function () {
    getQuote();
});

function getQuote() {
    fetch('/api/quote')
        .then(function (response) {
            return response.json();
        })
        .then(function (quote) {
            console.log(quote)
            $('#quote-content').html(quote.content);
            $('#quote-author').text("- "+quote.title);
            $('.quote-wrapper').removeClass('invisible');
        })
        .catch(function (error) {
            $('.quote-wrapper').removeClass('invisible');
        })
}