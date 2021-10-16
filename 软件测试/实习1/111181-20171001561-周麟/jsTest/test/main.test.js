//引入文件
import {howMuch,luggage} from './forTest'

test('Kp取空值',()=>{
    var pList = []
    pList.push(new luggage('',20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('Kp取[0,1,2,3]以外的值',()=>{
    var pList = []
    pList.push(new luggage(4,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('长取空值',()=>{
    var pList = []
    pList.push(new luggage(0,'',20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('宽取空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,'',20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('高取空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,'',20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('重量取空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,''))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('Kp取值非数字',()=>{
    var pList = []
    pList.push(new luggage('hhh',20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('长取非数字',()=>{
    var pList = []
    pList.push(new luggage(0,'hhh',20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('宽取非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,'hhh',20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('高取非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,'hhh',20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('重量取非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,'hhh'))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('长>100',()=>{
    var pList = []
    pList.push(new luggage(0,101,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('宽>60',()=>{
    var pList = []
    pList.push(new luggage(0,20,61,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('高>40',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,41,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('seatIndex输入为空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = ''
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('seatIndex输入为非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 'hhh'
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('seatIndex输入为[0,1,2,3]以外的数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 4
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('ticketIndex输入为空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = ''
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('ticketIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 'hhh'
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('ticketIndex取[0,1]以外的数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 2
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('personIndex输入空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = ''
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('personIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 'hhh'
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('personIndex在[0,1]以外取值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 2
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex输入空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = ''
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex输入[0,1]以外的值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 2
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 'hhh'
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex=1，whatGoByIndex空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = ''
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex=1，whatGoByIndex输入[0,1,2]以外的值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 3
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex=1，whatGoByIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 'hhh'
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex=1，whatGoByIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = ''
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex=1，whatAreaIndex为[0,1,2,3,4]以外的数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 5
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('flyIndex=1，whatAreaIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 'hhh'
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('cardIndex输入空值',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = ''
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('cardIndex输入[0,1,2,3]以外的数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 4
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('cardIndex输入非数字',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 'hhh'
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(-1)
})

test('计算对于国内航线，一个免费包裹应该支付的托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('计算对于国内航班，多个免费包裹应该支付的托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('计算对于国际航班，一个免费包裹应该支付的托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    const res = [0,0,0,0]
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual(res)
})

test('计算对于国际航班，多个免费包裹应该支付的托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    const res = [0,0,0,0]
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual(res)
})

test('一个免费的行李、头等舱、成人或儿童客票、普通旅客、国内航、无办卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、公务舱、成人或儿童客票、普通旅客、国内航、无办卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 1
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、悦享经济舱/超级经济舱、成人或儿童客票、普通旅客、国内航、无办卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 2
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、经济舱、成人或儿童客票、普通旅客、国内航、无办卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、经济舱、婴儿客票、普通旅客、国内航、无办卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、经济舱、婴儿客票、普通旅客、国内航、凤凰知音终生白金卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 1
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、经济舱、婴儿客票、普通旅客、国内航、凤凰知音金卡、银卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 2
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李、经济舱、婴儿客票、普通旅客、国内航、星空联盟金卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个非免费的行李、头等舱、成人或儿童客票、普通旅客、国内航、无办卡，应该要支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(1,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe('free')
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域一，无特殊办卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 0
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域一，凤凰知音终生白金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 1
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域一，凤凰知音金卡、银卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 2
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域一，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域二，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 1
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域三，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 2
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域四，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 3
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径夏威夷、欧洲、亚洲、非洲，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 0
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径美洲、加勒比地区、中东、日本、巴基斯坦、新加坡、哈萨克斯坦，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 1
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，成人或儿童客票，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，残疾、伤、病旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 0
    const personIndex = 1
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，头等舱，婴儿客票，残疾、伤、病旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 0
    const ticketIndex = 1
    const personIndex = 1
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，公务舱，婴儿客票，残疾、伤、病旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 1
    const ticketIndex = 1
    const personIndex = 1
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，悦享经济舱/超级经济舱，婴儿客票，残疾、伤、病旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 2
    const ticketIndex = 1
    const personIndex = 1
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，经济舱，婴儿客票，残疾、伤、病旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 1
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([0,0,0,0])
})

test('一个免费的行李，经济舱，婴儿客票，残疾、伤、病旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(1,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 1
    const personIndex = 1
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([1310,190,0,0])
})

test('一个免费行李，两个非免费的行李长宽高重量分别为20，20，20，200，经济舱，成人或儿童客票，普通旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(1,20,20,20,200))
    pList.push(new luggage(2,20,20,20,200))
    const seatIndex = 3
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([2350,340,0,0])
})

test('一个免费行李，两个非免费的行李长宽高重量分别为20，20，20，200，经济舱，成人或儿童客票，普通旅客，国内航班，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(1,20,20,20,200))
    pList.push(new luggage(2,20,20,20,200))
    const seatIndex = 3
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toBe(540)
})

test('一个免费行李，两个非免费的行李长宽高重量分别为20，20，20，20，经济舱，成人或儿童客票，普通旅客，国际、地区航班、途径其他地区，属于区域五，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(1,20,20,20,20))
    pList.push(new luggage(2,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 1
    const whatGoByIndex = 2
    const whatAreaIndex = 4
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual([3110,450,0,0])
})

test('一个免费行李，两个非免费的行李长宽高重量分别为20，20，20，20，经济舱，成人或儿童客票，普通旅客，国内航班，星空联盟金卡，应该支付多少托运费用',()=>{
    var pList = []
    pList.push(new luggage(0,20,20,20,20))
    pList.push(new luggage(1,20,20,20,20))
    pList.push(new luggage(2,20,20,20,20))
    const seatIndex = 3
    const ticketIndex = 0
    const personIndex = 0
    const flyIndex = 0
    const whatGoByIndex = 0
    const whatAreaIndex = 0
    const cardIndex = 3
    expect(howMuch(pList,seatIndex,ticketIndex,personIndex,flyIndex,whatGoByIndex,whatAreaIndex,cardIndex)).toStrictEqual('free')
})