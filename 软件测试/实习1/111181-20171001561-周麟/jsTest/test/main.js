//先检测是不是所有的参数都填了
const msg = document.querySelector('.msg')
const mainP = document.querySelector('#my-form')
const addPB = document.getElementById('addP')
const delPB = document.getElementById('delP')

addPB.addEventListener('click',(e)=>{
    e.preventDefault()
    // console.log('test')
    add_div()
})
delPB.addEventListener('click',(e)=>{
    e.preventDefault()
    // console.log('test')
    del_div()
})

var numOfPValue =1;

// console.log(numOfPValue)
//增加编辑行李的列数
function add_div() {
        var e = document.getElementById("luggage");
        // console.log(e)
        var div = document.createElement("div");
        div.className = "luggage-group";
        div.id = "luggage" + numOfPValue
        div.innerHTML = e.innerHTML;
        document.getElementById("pInfo").appendChild(div);
        numOfPValue++;
}
//减少编辑行李的列数
function del_div() {
    // console.log(e)
    if(numOfPValue>1){
        var id = "luggage" + (numOfPValue-1).toString();
        var e = document.getElementById(id);
        document.getElementById("pInfo").removeChild(e);
        numOfPValue--;
    }
}
//获取所有选定的值
//方便进行行李金额的计算
//相同的 先进行输入格式的检查
//可以通过id就可以获取所有值
mainP.addEventListener('submit', onSubmit);

//用一个容器记录所有行李的尺寸和它所属的类型
let pList = []
class luggage{
    constructor(kp,long,width,length,weight) {
        this._long = long;
        this._width = width;
        this._length = length;
        this._weight = weight;
        this._kindOf = kp;
    }


    get kindOf() {
        return this._kindOf;
    }

    getLong() {
        return this._long.value;
    }

    getWidth() {
        return this._width.value;
    }

    getLength() {
        return this._length.value;
    }

    getWeight() {
        return this._weight.value;
    }
}
//将所有选择都保存起来
//座舱类型
const whatSeat = document.getElementById('kindOfSeat')
//持票类型
const whatTicket = document.getElementById('kindOfTicket')
//航班类型
//如果是国际航班 还要填入出发点和目的地
const whatFly = document.getElementById('kindOfFly')
//持卡类型
const whatCard = document.getElementById('kindOfCard')
//旅客类型
const whatPerson = document.getElementById('kindOfCustom')

function onSubmit(e){
    e.preventDefault();
    const whatSeatIndex = whatSeat.selectedIndex
    const whatTicketIndex = whatTicket.selectedIndex
    const whatFlyIndex = whatFly.selectedIndex
    const whatCardIndex = whatCard.selectedIndex
    const whatPersonIndex = whatPerson.selectedIndex

    const first = document.getElementById('luggage')
    const fl = first.querySelector('#long')
    const fw = first.querySelector('#width')
    const fh = first.querySelector('#height')
    const fweight = first.querySelector('#weight')
    const selection = first.querySelector('#kindOfP')
    var whatAreaIndex = 0
    var whatGoByIndex = 0
    //什么区域的
    if(whatFlyIndex===1) {
        const whatArea = document.getElementById('area')
        whatAreaIndex = whatArea.selectedIndex
        //途径什么地点
        const whatGoBy = document.getElementById('goBy')
        whatGoByIndex = whatGoBy.selectedIndex
    }

    pList.push(new luggage(selection.selectedIndex,fl,fw,fh,fweight))
    for(let i=1;i<numOfPValue;i++){

        const temp = document.getElementById("luggage" + i.toString())
        const l = temp.querySelector('#long')
        const w = temp.querySelector('#width')
        const h = temp.querySelector('#height')
        const weight = temp.querySelector('#weight')
        const selection2 = temp.querySelector('#kindOfP')
        // console.log(typeof (selection2.selectedIndex))
        pList.push(new luggage(selection2.selectedIndex,l,w,h,weight))
        // console.log(pList[i].kindOf)
    }
    // console.log(`${whatSeatIndex} ${whatTicketIndex} ${whatPersonIndex} ${whatFlyIndex} ${whatGoByIndex} ${whatAreaIndex} ${whatCardIndex}`)
    howMuch(pList,whatSeatIndex,whatTicketIndex,whatPersonIndex,whatFlyIndex,whatGoByIndex,whatAreaIndex,whatCardIndex)

    pList = []
}

function howMuch(luggageList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex){

    //检查输入值下标的有效性
    //为空
    if(luggageList.kindOf===''||seatIndex===''||ticketIndex===''||personIndex===''||flyIndex===''||whatGoByIndex===''||whatAreaIndex===''||cardIndex===''){
        alert('传入参数为空')
        return -1
    }
    //非数字
    if(isNaN(seatIndex)||isNaN(ticketIndex)||isNaN(personIndex)||isNaN(flyIndex)||isNaN(whatGoByIndex)||isNaN(whatAreaIndex)||isNaN(cardIndex)){
        alert('传入参数非数字')
        return -1
    }
    //超出范围
    //indexOf

    if([0,1,2,3].indexOf(seatIndex)===-1|| [0,1].indexOf(ticketIndex)===-1||[0,1].indexOf(personIndex)===-1
        ||[0,1].indexOf(flyIndex)===-1 ||[0,1,2].indexOf(whatGoByIndex)===-1||[0,1,2,3,4].indexOf(whatAreaIndex)===-1
        ||[0,1,2,3].indexOf(cardIndex)===-1){
        alert('输入参数超出范围')
        return -1
    }
    //总重量
    var allWeight = 0
    //计算有多少个免费的行李
    var freeLuggage = 0
    for(let i=0;i<luggageList.length;i++){
        //参数超出范围
        if([0,1,2,3].indexOf(luggageList[i].kindOf)===-1){
            alert('输入参数超出范围')
            return -1
        }
        //为空
        if (luggageList[i].getLong() ==='' || luggageList[i].getWidth() ==='' || luggageList[i].getLength() ==='' || luggageList[i].getWeight() ==='') {
            alert('输入值为空')
            return -1
        }
        //非数字
        if (isNaN(luggageList[i].kindOf)||isNaN(luggageList[i].getLong()) || isNaN(luggageList[i].getWidth()) || isNaN(luggageList[i].getLength()) || isNaN(luggageList[i].getWeight())) {
            alert('Please Input The Right Data!')
            return -1
        }
        //小于0
        if (luggageList[i].getLong() < 0 || luggageList[i].getWidth() < 0 || luggageList[i].getLength() < 0 || luggageList[i].getWeight() < 0) {
            alert('Wrong Input！')
            return -1
        }
        //超出最大接受范围
        if (Number(luggageList[i].getLong()) + Number(luggageList[i].getWidth()) + Number(luggageList[i].getLength()) > 203
            || luggageList[i].getLong() > 100 || luggageList[i].getWidth() > 60 || luggageList[i].getLength() > 40) {
            // console.log(`${luggageList[i].getLong()}  ${luggageList[i].getWidth()}  ${luggageList[i].getLength()}`)
            // console.log(Number(luggageList[i].getLong()) + Number(luggageList[i].getWidth()) + Number(luggageList[i].getLength()))
            alert('行李尺寸超出范围')
            return -1
        }
        if(luggageList[i].kindOf!==0){
            allWeight+=Number(luggageList[i].getWeight())
        }else{
            //有免费行李
            // console.log('test')
            freeLuggage+=1
        }
    }
    // 确定所有值都保存了
    //接下来就开始计算托运价格了
    var rangeWeight = 0;
    //没有规定国内票价
    //那么返回的结果就是票价的百分之几之几
    //国内航班好写一点
    //这个地方就是逻辑部分
    // console.log(flyIndex)
    if(flyIndex===0){
        //普通卡
        if(cardIndex===0){
            rangeWeight = 0
        }
        //凤凰知音终生白金卡
        else if(cardIndex===1){
            rangeWeight = 30
        }else {
            rangeWeight = 20
        }
        //不同的客舱
        if(personIndex===0){
            if(seatIndex===0){
                rangeWeight+=40
            }else if(seatIndex===1){
                rangeWeight+=30
            }else{
                rangeWeight+=20
            }
        }else{
            rangeWeight+=10
        }
        var num = (allWeight%32===0)?parseInt(allWeight/32):parseInt(allWeight/32)+1
        //开始计算价格

        // console.log(`${allWeight}    ${rangeWeight}`)
        if(allWeight<=rangeWeight){
            alert(`所有行李免费托运 但是要打包成 ${num+freeLuggage} 个行李`)
            return 'free'
        }else{
            //超出界限
            var overSize = allWeight - rangeWeight
            overSize*=1.5
            alert(`需要支付经济舱普通票价的${overSize}%  并且打包成${num+freeLuggage} 个行李`)
            return overSize
        }
    }else{
        //国际航班
        //为了防止重复操作
        //switch修改边缘值然后在进行计算即可
        // alert(`别催了 还没写 谢谢`)
        //case1 超重量但不超尺寸

        var c1WLow = 23
        var c1WHigh = 28
        var c1sLow = 60
        var c1sHigh = 158
        //case2 超重量但不超尺寸
        var c2WLow = 28
        var c2WHigh = 32
        var c2sLow = 60
        var c2sHigh = 158
        //case3 不超重量但超尺寸
        var c3WLow = 2
        var c3WHigh = 23
        var c3sLow = 158
        var c3sHigh = 203
        //case4 超重量且超尺寸
        var c4WLow = 23
        var c4WHigh = 32
        var c4sLow = 158
        var c4sHigh = 203

        //因为舱位 或者 票型而造成的免费托运行李限额的不同
        var canFree
        var rangeWeight
        //超出的额外行李
        var numOver
        if(ticketIndex===0&&(seatIndex===0||seatIndex===1)){
            canFree = 2
            rangeWeight = 32
        }
        //悦享经济舱、超级经济舱
        else if(seatIndex===2){
            canFree = 2
            rangeWeight = 23
        }
        //经济舱
        else{
            rangeWeight = 23
            if(whatGoByIndex ===0){
                canFree = 1
            }else if(whatGoByIndex===1){
                canFree = 2
            }else{
                canFree = 0
            }
        }
        var rmb = 0
        var dollar = 0
        var euro = 0
        var jYuan = 0
        var case1 = false
        var case2 = false
        var case3 = false
        var case4 = false
        //超过的行李数
        var moreP = 0

        var c1RNB = 0
        var c1Dollar = 0
        var c1Euro = 0
        var c1jYuan = 0

        var c2RNB = 0
        var c2Dollar = 0
        var c2Euro = 0
        var c2jYuan = 0

        var c3RNB = 0
        var c3Dollar = 0
        var c3Euro = 0
        var c3jYuan = 0

        var c4RNB = 0
        var c4Dollar = 0
        var c4Euro = 0
        var c4jYuan = 0

        //超出行李数需付金钱
        var overOneRMB = 0
        var overOneDollar = 0
        var overOneEuro = 0
        var overOneJYuan = 0

        var overTwoRMB = 0
        var overTwoDollar = 0
        var overTwoEuro = 0
        var overTwoJYuan = 0

        var overThreeRMB = 0
        var overThreeDollar = 0
        var overThreeEuro = 0
        var overThreeJYuan = 0

        if(whatAreaIndex===0){
            c1RNB = 380
            c1Dollar = 60
            c2RNB = 980
            c2Dollar = 150
            c3RNB = 980
            c3Dollar = 150
            c4RNB = 1400
            c4Dollar = 220

            overOneRMB = 1400
            overOneDollar = 220

            overTwoRMB = 2000
            overTwoDollar = 310

            overThreeRMB = 3000
            overThreeDollar = 460
        }else if(whatAreaIndex===1){
            c1RNB = 280
            c1Dollar = 40
            c1Euro = 35

            c2RNB = 690
            c2Dollar = 100
            c2Euro = 85

            c3RNB = 690
            c3Dollar = 100
            c3Euro = 85

            c4RNB = 1100
            c4Dollar = 160
            c4Euro = 140

            overOneRMB = 1100
            overOneDollar = 160
            overOneEuro = 140

            overTwoRMB = 1100
            overTwoDollar = 160
            overTwoEuro = 140

            overThreeRMB = 1590
            overThreeDollar = 230
            overThreeEuro = 200
        }else if(whatAreaIndex===2){
            c4RNB = 520
            c4Dollar = 75
            c4jYuan = 100

            overOneRMB = 1170
            overOneDollar = 170
            overOneJYuan = 225

            overTwoRMB = 1170
            overTwoDollar = 170
            overTwoJYuan = 225

            overThreeRMB = 1590
            overThreeDollar = 230
            overThreeJYuan = 300
        }
        else if(whatAreaIndex===3){
            c1RNB = 690
            c1Dollar = 100

            c2RNB = 1040
            c2Dollar = 150

            c3RNB = 1040
            c3Dollar = 150

            c4RNB = 2050
            c4Dollar = 300

            overOneRMB = 1380
            overOneDollar = 200

            overTwoRMB = 1380
            overTwoDollar = 200

            overThreeRMB = 1590
            overThreeDollar = 230
        }else{
            c1RNB = 210
            c1Dollar = 30

            c2RNB = 520
            c2Dollar = 75

            c3RNB = 520
            c3Dollar = 75

            c4RNB = 830
            c4Dollar = 120

            overOneRMB = 830
            overOneDollar = 120

            overTwoRMB = 1100
            overTwoDollar = 160

            overThreeRMB = 1590
            overThreeDollar = 230
        }
        for(let i =0;i<luggageList.length;i++){
            //先判断有没有行李是不符合国际托运原则的
            //而且是根据不同的票型
            //穷人真惨
            //如果这个是免费的行李就不用计算
            if(luggageList[i].kindOf===0)continue
            if(Number(luggageList[i].getLong())+Number(luggageList[i].getWidth())+Number(luggageList[i].getLength())>203
                // || luggageList[i].getWeight()>rangeWeight
            ){
                alert('行李尺寸不符合国际、地区航班的规定')
                return NaN
            }
            const s = Number(luggageList[i].getLength())+Number(luggageList[i].getWidth())+Number(luggageList[i].getLong())

            //如果尺寸、重量都没有超过 并且免费限额都没有用完
            if(luggageList[i].getWeight()<=rangeWeight&&s<=158&&canFree>0){
                //那就不需要另外计算金钱
                canFree-=1
                continue
            }
            //如果尺寸、重量都没有超过 免费限额用完
            if(luggageList[i].getWeight()<=rangeWeight&&s<=158&&canFree===0){
                //头等、公务舱and成人或儿童客票
                moreP+=1
            }
            if(luggageList[i].getWeight()>rangeWeight||158<s<=203){
                moreP+=1
            }
            //尺寸没有超过、重量超过
            if(c1WLow<luggageList[i].getWeight()<=c1WHigh&&c1sLow<=s<=c1sHigh){
                case1 = true
            }
            //尺寸没有超过、重量超过
            if(c2WLow<luggageList[i].getWeight()<=c2WLow&&c2sLow<=s<=c2sHigh){
                case2 = true
            }
            //如果尺寸超过、重量都没有超过
            if(c3WLow<=luggageList[i].getWeight()<=c3WHigh&&c3sLow<s<=c3sHigh){
                case3 = true
            }
            //尺寸超过、重量超过
            if(c4WLow<=luggageList[i].getWeight()<=c4WHigh&&c4sLow<=s<=c4sHigh){
                case4 = true
            }
            if(moreP===1){
                rmb += overOneRMB
                dollar += overOneDollar
                euro += overOneEuro
                jYuan += overOneJYuan
            }else if(moreP===2){
                rmb += overTwoRMB
                dollar += overTwoDollar
                euro += overTwoEuro
                jYuan += overTwoJYuan
            }else if(moreP>=3){
                rmb += overThreeRMB
                dollar += overThreeDollar
                euro += overThreeEuro
                jYuan += overThreeJYuan
            }
            if(case1){
                case1 = false
                rmb += c1RNB
                dollar +=c1Dollar
                euro +=c1Euro
            }else if(case2){
                case2 = false
                rmb += c2RNB
                dollar +=c2Dollar
                euro +=c2Euro
            }else if(case3){
                case3 = false
                rmb += c3RNB
                dollar += c3Dollar
                jYuan += c3jYuan
            }else if(case4){
                case4 = false
                rmb += c4RNB
                dollar += c4Dollar
                euro += c4Euro
            }
        }
        //最后就可以打印结果了
        alert(`需要支付${rmb}人民币  ${dollar}美元   ${euro}欧元   ${jYuan}加元`)
        return [rmb,dollar,euro,jYuan]
    }
}
