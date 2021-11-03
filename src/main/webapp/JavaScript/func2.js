//func2.js
let str = '<h1>Hello</h1>';  //let이라는 키워드로 문자열을 닮아줌
str += '<ul><li>Apple</li><li>Banana</li><li>Cherry</li></ul>';
// str이라는 구문에다가 '<h1>Hello</h1>');내용을 추가해서 다시 str변수에 담아주겠다
document.write(str);

let li = document.querySelector('ul>li');
console.log(li);
// li.remove();
li.style.color = 'red';
li.style.backgroundColor = 'yellow';

console.log(this.document.body); //document>body
console.log(this); //this전역객체  //object => {key : value, key1 : value1}형식으로 값이 구성되어있음
obj = {
    key : 'value',
    key1: 'value1',
    key2: {
        key3: 'value3',
        key4: 'value4',
        key5: {
            key6: 'value6',
            key7: {
                key8:'value8'
            }
        }
    }
}
obj.key; //value를 가져오게 됨
obj.key2.key3; //value 3을 읽어옴
console.log(obj.key2.key5.key6);
//==
console.log(obj.key2.key5['key6']);
//=
console.log(obj.key2['key5']['key6']);
// 배열기호 안에다가 필드이름을 적으주어도.....똑같이나오게 됨