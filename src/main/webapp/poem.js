let poemsTableEl;
let poemsTableBodyEl;

function onPoemClicked() {
    const poemId = this.dataset.poemId;

    const params = new URLSearchParams();
    params.append('id', poemId);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/poems?' + params.toString());
    xhr.send();
}

function onPoemsLoad(poems) {
    poemsTableEl = document.getElementById('poems');
    poemsTableBodyEl = poemsTableEl.querySelector('tbody');

    appendPoems(poems);
}

function onPoemsResponse() {
    if (this.status === OK) {
        showContents(['poems-content', 'back-to-profile-content', 'logout-content']);
        onPoemsLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(poemsContentDivEl, this);
    }
}