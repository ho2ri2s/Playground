'use strict';
const http = require('http');
const server = http.createServer((req, res) => {
    const now = new Date();
    console.info('[' + now + '] Requested by ' + req.connection.remoteAddress);
    res.writeHead(200, {
        'Content-Type': 'text/plain; charset=utf-8'
    });
    // requestで使われたメソッドで判定
    switch (req.method) {
        case 'GET':
            res.write('GET ' + req.url);
            break;
        case 'POST':
            //curlで -dオプションをつけるとPOSTでリクエストが飛ぶ
            res.write('POST ' + req.url);
            let rawData = '';
            req.on('data', (chunk) => {
                rawData = rawData + chunk;
            }).on('end', () => {
                console.info('[' + now + '] Data posted: ' + rawData);
            });
            break;
        case 'DELETE':
            res.write('DELETE' + req.url);
            break;
        default:
            break;
    }
    res.end();
}).on('error', (e) => {
    console.error('[' + new Date() + '] Server Error', e);
}).on('clientError', (e) => {
    console.error('[' + new Date() + '] Client Error', e);
});
const port = 8000;
server.listen(port, () => {
    console.log('Listening on ' + port);
})

/**
 * createServerメソッドの無名関数は、リクエストがあった際に行う処理を書いている。
 * writeはレスポンスの内容を書き出している。
 * 終わればhttp.ServerResponse#endを実行する。
 * 
 * {listen}メソッドはHTTPサーバーを起動する関数。特定のポートからリクエストがあったときに無名関数を実行する。
 * 今回はローカルホストである8000をlistenしている。
 * 
 * user-agentとは、利用者がHTTP通信をする際のソフトウェアorハードウェア。
 */

/**
 * ページを1度表示するだけで2回アクセスされるのは、1回目のアクセスで/favicon.icoにアイコン画像がないか確認する。
 */