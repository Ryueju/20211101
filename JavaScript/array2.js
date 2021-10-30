// array2.js

const numbers = [3, 34, 28 ,12 , 5];

//반복문을가지고 반복문을 적어줘도 되고
// 배열에는 forEach 라는 메소드가 있음 배열의 각각 요소에 대해서 어떤 기능을 실행하겠다, 어떤 처리들을 넣겠다.
let sum = 0;
numbers.forEach(function(val,ind,ary){
//배열에 들어있는 각각의 요소를 sum 하겠다.
// if(ind %2 ==0) //홀수번 째 값만 더하겠다.
// sum += val;
// if(val > 10) //10보다 큰 값만 더할게
// sum += val;
// document.write(`<h1> ${val} </h1>`);
});
console.log(`합계: ${sum}`);

//object만드는 방식 간단하게
//생성자 함수를 활용하자 (어휴 ;)
function User(id, name, point){ //생성자 함수 // U 대문자는 생성자 함수임을 나타내려고
    this.id = id;
    this.name = name;
    this.point = point;
}
//new라는 키워드로
// new User('user1','포챠코',90);
const users  = [new User('user1','포챠코',90),
new User('user2','시나모롤',100),
new User('user3','쿠로미',200),
new User('user4','마이멜로디',500)
]; 

console.log(users);

let str = '<h1>회원리스트</h1>'; //먼저 변수선언하기
str += '<table border="1"> ';
str +='<tbody>';
users.forEach(callBackFnc); //callBackFnc를 나중에 불러내겠다..
str+= '</tbody>';
str+='</tabld>';
document.write(str);

function callBackFnc(val, ind, ary){
    //콜백함수를 간단하게 하기위해서 메소드 안에 대신 넣어주고 정의해줌
    str += '<tr><td>' + val.id +'</td>' +'<td>' +  val.name  +'</td>'+'<td>'+ val.point + '</td></tr>';
}