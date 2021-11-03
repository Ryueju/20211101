// array1.js
//사용자가값을 입력하고 버튼 이벤트로 사용자가 입력한 값을 추가하도록 프로그램을 구현해보자
const persons = []; //persons라는 배열을 먼저 선언하자 persons라는 배열에 값을 추가할 건데 
function addFnc(){
    let addBtn = document.getElementById('add'); //id 요소가 add인 값을 찾아오겠다..
    // console.log(addBtn);
    let val = addBtn.value; //val 이라는 변수를 선언해서 계속 선언하지 않도록 해도 됨
    console.log(val); // addBtn.value  addBtn안에 있는 value값을 콘솔에 보여줌

    //추가 된 값을 배열에 하나씩 추가해보자
    // persons.push(val); //배열에 요소를 하나씩 추가함.
//push 대신에 unshift가 들어가게 되면 배열 첫번째에 추가하게됨
    persons.unshift(val);
    console.log(persons);
}
function removeFnc(){
    // persons.pop(); //맨뒤의 값 부터 삭제
    // persons.shift(); //맨 앞의 값부터 삭제
    let finedVal = document.getElementById('add').value;
    let cnt = -1; //cnt는 removeFnc() function안에서만 의미 있음 //-1인 이유초깃값이 0이되면 조건만족하지 않을경우 인덱스 0이삭제
    for(let i = 0; i < persons.length; i++){ //cnt 선언 이유 : let i 가 블록 안에서 선언되어서 밖에서 쓰지 못하기 떄문에 밖에서 쓰려고
        //persons가 갖고있는 length보다 작을 동안 반복해주는데
        // id값이 add인 놈 중에서.. 똑같은 놈 찾아오자?
        //input 태그의 value속성을 가져와
        if(persons[i] === finedVal){
            cnt = i; //cnt값의 위치를 알려주든지? 아님.......
            break; 

        }
    }
        if(cnt>=0){
            persons.splice(cnt,1);
        }
    persons.splice(3,1); 

    console.log(persons);
}
document.write('<input type = "text" id ="add"> ');
document.write('<button onclick="addFnc()">추가</button>');
document.write('<button onclick="removeFnc()">삭제</button>');
//addfnc()라는 fucntion 필요함. function addFnc(){}

// const ary  = new Array(); //['hong','hwang'];
const ary = ['hong','hwang'];
console.log(ary[0])
ary[0] = 'hong1'; //new 라는 키워드로만들어도 되지만 보기쉽게 [] 를 넣어서 배열을 만들수 있음
ary[1] = 'hwang1'; 
ary[2] = 'park';
ary[ary.length] = 'kim'; //ary가 갖고 있는 length만큼 //마지막 위치에 값을 너헝주게 됨
ary[4] = 'po';
ary[ary.length] = 'lee';
ary.push('new');
ary.push('new2');
//push 제일 마지막 위치에다 값을 추가하는 메소드

delete ary[3];
// 특정한 값을 지우는 메소드 delete
// ary.pop(); //제일 마지막 값을 삭제함.
ary.splice(3,2,'new park','new kim','new lee'); //매개값이 몇가지올 수 있는데 첫번째 값은 인덱스의 위치, 두번째 값은 인덱스에서 시작해서 몇개까지
// 3부터 2개선택, 즉 3,4를 가져오겠다....는 뜻 kim과 po의 값을 대체 
//뒤에 대체 값이 없으면 그대로 삭제됨.
//delete는 undefined 값이 나오므로 splice는 지워주고 빈 공간이 생기지 않도록 해줌!
ary.splice(3,0,'new kim'); 
//0은 대체 가 아니라 'new kim'값을 추가하겠다는 뜻
//splice ( index, size, replace)
ary.unshift('first'); //배열의 제일 앞부분에 값을 추가 
ary.shift();//제일 앞쪽의 값을 삭제하겠다

// for( let i =0; i<ary.length; i++){
//     console.log(ary[i]);  
// }
console.log('--------------------------');
for(let val of ary){
    //배열에 든 요소를 반복하겠다는 뜻
    console.log(val); 
//단순히 배열에 들어있는 값만 필요한 경우 for  of
}

const newAry = ary.slice(1,2); //인덱스 값 1 부터 2 까지인데 이제 2는 제외된. slice(include, exclude)
console.log(newAry);

const mergeAry = ary.concat(newAry); // 두 개의 배열을 합치겠다..
console.log(mergeAry);

mergeAry.sort(); //정렬
mergeAry.reverse();// 역순정렬
console.log(mergeAry);

const numbers = [2,10,100,24,4];
numbers.sort(function(f,l){ //오름차순이라는 뜻
    //콜 백 함 수 ? ex soft(functin(){})
        // if(f<l){
        //     return -1; //f다음에 l을 놓겠다는 뜻
        // } else {
        //     return 1; //그렇지 않으면 양수값을 리턴할게. 
        // }
        //간단하게 해보자
        // return f -l; /오름차순 정렬
            return l - f; //내림차순
}); //numbers 배열을 정렬하겠다.
console.log(numbers); //기준을 지정해주지 않으면 그냥 사전순으로 정렬해줌
//크기순으로 하고시펑
//sort라는 메소드 안에 정렬기준을 넣어줘야한다. sort(정렬기준);




const s1 = { //오브젝트 타입
    name: 'pochaco',
    score : 85
}
const s2 = {
    name:'kuromi',
    score: 80
}
const s3 = {
    name: 'mymelody',
    score: 90
}

const students = [s1, s2, s3];
students.sort(function(f,l){
    //sort라는 매소드에 f,l이 원래 정의가 되어있음
    // // if(f.name < l.name){ //이름순 정렬
    //     //크다 작다는 음수 또는 양수로 리턴해주면 됨 //음수는 오름차순 //양수는 내림차순!
    //     return -100;
    // } else {
    //     return 1;
    // }
    if(f.score < l.score){ //점수로 정렬
        //크다 작다는 음수 또는 양수로 리턴해주면 됨 //음수는 오름차순 //양수는 내림차순!
        return -100;
    } else {
        return 1;
    }
});

console.log(students);