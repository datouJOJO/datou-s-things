/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 35.15935186178775, "KoPercent": 64.84064813821226};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.06349930250026826, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.0, 500, 1500, "HTTP Request-1"], "isController": false}, {"data": [0.274021764297291, 500, 1500, "HTTP Request-0"], "isController": false}, {"data": [0.0, 500, 1500, "HTTP Request"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 18638, 12085, 64.84064813821226, 35588.86296812967, 1, 2187845, 21094.0, 55084.50000000003, 74986.29999999996, 125565.50000000015, 8.51879157298723, 179.83527953598912, 0.6349553920961446], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["HTTP Request-1", 4319, 3202, 74.13753183607317, 55059.635795323025, 1121, 2186910, 21141.0, 73781.0, 99224.0, 2184188.4000000004, 1.974604907572123, 85.70501828575468, 0.07459524085470812], "isController": false}, {"data": ["HTTP Request-0", 4319, 0, 0.0, 4925.816624218577, 498, 95349, 1067.0, 21429.0, 26146.0, 37178.000000000015, 44.0790749415715, 17.562756422032393, 5.42379242445118], "isController": false}, {"data": ["HTTP Request", 10000, 8883, 88.83, 40422.80590000006, 1, 2187845, 21310.0, 58591.09999999998, 75991.84999999995, 124667.36999999998, 4.570657566792161, 93.36706593479202, 0.3174776960480723], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Socket closed", 95, 0.7860984691766653, 0.5097113424187144], "isController": false}, {"data": ["Non HTTP response code: javax.net.ssl.SSLHandshakeException/Non HTTP response message: Remote host closed connection during handshake", 768, 6.354985519238726, 4.120613799763923], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 2408, 19.925527513446422, 12.919841184676468], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:80 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 3925, 32.47827885808854, 21.05912651572057], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Permission denied: connect", 2, 0.016549441456350848, 0.010730765103551884], "isController": false}, {"data": ["Non HTTP response code: javax.net.ssl.SSLException/Non HTTP response message: SSL peer shut down incorrectly", 942, 7.794786925941249, 5.054190363772937], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.NoHttpResponseException/Non HTTP response message: searchcode.com:80 failed to respond", 360, 2.978899462143153, 1.931537718639339], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Connection reset", 319, 2.6396359122879605, 1.7115570340165254], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Unrecognized Windows Sockets error: 0: recv failed", 310, 2.5651634257343816, 1.6632685910505418], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketException/Non HTTP response message: Software caused connection abort: recv failed", 41, 0.3392635498551924, 0.21998068462281362], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Read timed out", 848, 7.01696317749276, 4.549844403905999], "isController": false}, {"data": ["500/Internal Server Error", 418, 3.4588332643773274, 2.2427299066423436], "isController": false}, {"data": ["Non HTTP response code: org.apache.http.NoHttpResponseException/Non HTTP response message: searchcode.com:443 failed to respond", 526, 4.352503103020273, 2.8221912222341454], "isController": false}, {"data": ["Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 1123, 9.292511377741, 6.025324605644382], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 18638, 12085, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:80 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 3925, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 2408, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 1123, "Non HTTP response code: javax.net.ssl.SSLException/Non HTTP response message: SSL peer shut down incorrectly", 942, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Read timed out", 848], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["HTTP Request-1", 4319, 3202, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 1204, "Non HTTP response code: javax.net.ssl.SSLException/Non HTTP response message: SSL peer shut down incorrectly", 471, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Read timed out", 424, "Non HTTP response code: javax.net.ssl.SSLHandshakeException/Non HTTP response message: Remote host closed connection during handshake", 384, "Non HTTP response code: org.apache.http.NoHttpResponseException/Non HTTP response message: searchcode.com:443 failed to respond", 263], "isController": false}, {"data": [], "isController": false}, {"data": ["HTTP Request", 10000, 8883, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:80 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 3925, "Non HTTP response code: org.apache.http.conn.HttpHostConnectException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Connection timed out: connect", 1204, "Non HTTP response code: java.net.SocketTimeoutException/Non HTTP response message: Read timed out", 1123, "Non HTTP response code: javax.net.ssl.SSLException/Non HTTP response message: SSL peer shut down incorrectly", 471, "Non HTTP response code: org.apache.http.conn.ConnectTimeoutException/Non HTTP response message: Connect to searchcode.com:443 [searchcode.com/78.47.90.135] failed: Read timed out", 424], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
