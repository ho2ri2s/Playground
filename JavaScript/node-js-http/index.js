'use strict';
const http = require('http');
const pug = require('pug');
const server = http.createServer((req, res) => {
    const now = new Date();
    console.info('[' + now + '] Requested by ' + req.connection.remoteAddress);
    res.writeHead(200, {
        'Content-Type': 'text/html; charset=utf-8'
    });
    // requestで使われたメソッドで判定
    switch (req.method) {
        case 'GET':
            if (req.url === '/enquetes/yaki-shabu') {
                res.write(pug.renderFile('./form.pug', {
                    path: req.url,
                    firstItem: '焼肉',
                    secondItem: 'しゃぶしゃぶ'
                }));
            } else if (req.url === '/enquetes/rice-bread') {
                res.write(pug.renderFile('./form.pug', {
                    path: req.url,
                    firstItem: 'ごはん',
                    secondItem: 'パン'
                }));
            };
            res.end();
            //パイプ：読み込みStreamと書き込みStreamをそのままつないでデータを受け渡すことができる。
            break;
        case 'POST':
            //curlで -dオプションをつけるとPOSTでリクエストが飛ぶ
            res.write('POST ' + req.url);
            let rawData = '';
            req.on('data', (chunk) => {
                rawData = rawData + chunk;
            }).on('end', () => {
                const decoded = decodeURIComponent(rawData);
                console.info('[' + now + '] 投稿： ' + decoded);
                res.write('<!DOCTYPE html><html lang="ja"><body><h1>' +
                    decoded + 'が投稿されました</h1></body></html>');
                res.end();
            });
            break;
        case 'DELETE':
            //curlで -XオプションをつけるとDELETEリクエストが飛ぶ
            res.write('DELETE' + req.url);
            break;
        default:
            break;
    }
}).on('error', (e) => {
    console.error('[' + new Date() + '] Server Error', e);
}).on('clientError', (e) => {
    console.error('[' + new Date() + '] Client Error', e);
});
const port = process.env.PORT || 8000;
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