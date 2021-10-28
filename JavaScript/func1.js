//func1.js
console.log('func1.js');

sum(10,20); //sum 실행구문 //function,변수선언 위로 옮겨도 가능함 =호이스팅(끌어올리다)
function sum(num1 , num2){
    //sum이라는 function을 실행하겠다, 근데 이제 변수 두개를 곁들인
    //num1,num2 sum이라는 함수 안에서 변수로 쓰이겠음
    let result = num1 + num2;
    console.log(result);
}

let multi = function(n1,n2){
    let result = n1 * n2;
    //두개의 값을 곱한 다음 결과 값을 반환해주겟다.
    return result;
}
let val = multi(2,5); //반드시 선언문 다음에 위치해야함
console.log(val);