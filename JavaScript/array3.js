// array3.js (Array map , Array filter)

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

const newUsers = users.map(function(val, ind, ary){ //,map새로운 배열을 생성하겠다.
    //변형된 새로운 타입의
    let newObj = {}//{}object타입만 정의한 것임. 필드가 들어가지 않은 상태
    newObj.uid = val.id;
    newObj.uname = val.name; //value가 가지고 있는 name속성을 담아줄게
    newObj.score = val.point;
    newObj.idx = ind;
    //obj를 선언할 땐 중괄호로 선언함
    return newObj;
    //아무 값이나 리턴 가능한
    // return val.id + '-' +  val.name; //리턴 값을 설저해 줄 수 이 ㅆ우
})
console.log(newUsers);

const filterUsers = newUsers.filter(function(val, ind){
    return val.idx; //값이 있는 것만 반환하겠음
});//return 참이면 반환/ 그렇지 않으면 반환안해
console.clear();
console.log(filterUsers);

//reduce는 생략!
// let resultAry = [];
// let sum = filterUsers.reduce(function(prevObj, curObj, ind, ary){ //sum : 초기값의 역할을 함
//     // console.log(a,b,c,d);
//     console.log(prevObj ,curObj);
//     // return prevObj.score + curObj.score;
//     // return (prevObj +curObj.score);//두개 합한 값의 다음 순번의 초기값으로 쓰겠다.
//     return prevObj.score + curObj.score;
// }); //
// // console.log('합계 : ' + sum);
// // ==
// console.log(`합계 : ${sum }` );
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