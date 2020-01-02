'use strict'
const todo = require('./index.js');
const assert = require('assert');

//todo と list のテスト
todo.todo('ノートを買う');
todo.todo('鉛筆を買う');
assert.deepEqual(todo.list(), ['ノートを買う', '鉛筆を買う']);
todo.done('ノートを買う');
assert.deepEqual(todo.donelist(), ['ノートを買う']);
todo.del('鉛筆を買う');
assert.deepEqual(todo.list(), []);
console.log('テストが正常に完了しました');