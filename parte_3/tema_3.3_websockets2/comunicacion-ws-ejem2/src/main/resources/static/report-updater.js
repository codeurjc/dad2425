window.addEventListener('load', (event) => {

    console.log("WebSocket")

    let protocol = window.location.protocol === 'https:' ? 'wss://' : 'ws://';
    let socket = new WebSocket(protocol + window.location.host + "/notifications?reportId=" + reportId);

    socket.onmessage = function (event) {

        console.log(`[message] Data received from server: ${event.data}`);

        let report = JSON.parse(event.data);

        let progressElem = document.getElementById('progress');
        let reportDataElem = document.getElementById('report-data');

        progressElem.textContent = report.progress + '%';
        reportDataElem.textContent = report.reportData;

    };

});

