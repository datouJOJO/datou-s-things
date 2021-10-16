from appium import webdriver
import time
# 初始化参数
from appium.webdriver.common.touch_action import TouchAction

desired_caps = {
    'platformName': 'Android',  # 被测手机是安卓
    'platformVersion': '10',  # 手机安卓版本
    'deviceName': 'xxx',  # 设备名，安卓手机可以随意填写
    'appPackage': 'tv.danmaku.bili',  # 启动APP Package名称
    'appActivity': '.ui.splash.SplashActivity',  # 启动Activity名称
    'unicodeKeyboard': True,  # 使用自带输入法，输入中文时填True
    'resetKeyboard': True,  # 执行完程序恢复原来输入法
    'noReset': True,  # 不要重置App，如果为False的话，执行完脚本后，app的数据会清空，比如你原本登录了，执行完脚本后就退出登录了
    'newCommandTimeout': 6000,
    'automationName': 'UiAutomator2'
}
# 连接Appium Server，初始化自动化环境
driver = webdriver.Remote('http://localhost:4723/wd/hub', desired_caps)
# 退出程序，记得之前没敲这段报了一个错误 Error: socket hang up 啥啥啥的忘记了，有兴趣可以try one try
# driver.implicitly_wait(20)
time.sleep(7)
el1 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget"
                                   ".HorizontalScrollView/android.widget.LinearLayout/android.view.ViewGroup[4]")
el1.click()
time.sleep(2)
el2 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView"
                                   "/android.widget.FrameLayout["
                                   "2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]")
el2.click()
time.sleep(2)
el3 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".RelativeLayout/android.view.ViewGroup["
                                   "2]/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView"
                                   "/android.widget.RelativeLayout[1]/android.widget.FrameLayout["
                                   "1]/android.widget.RelativeLayout/android.widget.ImageView")
el3.click()
time.sleep(2)
el4 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                   ".ViewGroup/android.widget.RelativeLayout/android.widget.FrameLayout/androidx"
                                   ".viewpager.widget.ViewPager/android.widget.RelativeLayout/androidx.recyclerview"
                                   ".widget.RecyclerView/android.widget.LinearLayout["
                                   "3]/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout["
                                   "2]/android.widget.FrameLayout/android.widget.LinearLayout")
el4.click()
time.sleep(2)
el5 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                   ".ViewGroup/android.widget.LinearLayout["
                                   "2]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout[1]/android.widget.FrameLayout["
                                   "2]/android.widget.FrameLayout/android.view.View")
el5.click()
time.sleep(2)
# tv.danmaku.bili:id/bbplayer_pause_btn
seekBar = driver.find_element_by_xpath('//android.widget.SeekBar[@content-desc="bbplayer_seekbar"]')
# 获取到seekbar控件本身的（尺寸）宽度
startX = seekBar.size.get('width')
# 获取控件中间位置y坐标，（试过后验发现不加尺寸的一半也能操作）
# 控件的坐标Y(应该是左上角的坐标)+控件本身高度的一半
startY = seekBar.location.get('y') + (seekBar.size.get('height')) / 2
# 通过控件的坐标滑动seekbar，从seekBar的1/4处滑动到3/4处
driver.swipe(startX / 4, startY, startX * 3 / 4, startY, 3000)
time.sleep(4)
# 通过控件的坐标滑动seekbar，从seekBar的3/4处滑动到1/4处
driver.swipe(startX * 3 / 4, startY, startX / 4, startY, 3000)
time.sleep(4)
# 全屏
TouchAction(driver).tap(x=546, y=311).perform()
time.sleep(1)
el19 = driver.find_element_by_id("tv.danmaku.bili:id/bbplayer_halfscreen_expand")
el19.click()
time.sleep(2)
# 取消全屏
driver.back()
# 关注该番
el4 = driver.find_element_by_id("tv.danmaku.bili:id/ll_follow")
el4.click()
time.sleep(2)
# 取消关注该番
el5 = driver.find_element_by_id("tv.danmaku.bili:id/ll_follow")
el5.click()
el6 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget"
                                   ".LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget"
                                   ".RelativeLayout[4]")
el6.click()
driver.back()
driver.back()
el7 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget"
                                   ".HorizontalScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]")
el7.click()
time.sleep(1)
el7 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget"
                                   ".HorizontalScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]")
el7.click()
time.sleep(2)
el8 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget"
                                   ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx"
                                   ".recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                   "4]/android.widget.FrameLayout/android.widget.ImageView")
el8.click()
time.sleep(2)
TouchAction(driver).tap(x=1000, y=200).perform()
TouchAction(driver).tap(x=1011, y=130).perform()
time.sleep(1)
el12 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                    ".LinearLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget"
                                    ".LinearLayout["
                                    "2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                    "1]/android.widget.TextView")
el12.click()
driver.back()

el8 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget"
                                   ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx"
                                   ".recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                   "4]/android.widget.FrameLayout/android.widget.ImageView")
el8.click()
time.sleep(2)

TouchAction(driver).tap(x=1000, y=200).perform()
TouchAction(driver).tap(x=1017, y=142).perform()
time.sleep(1)
el13 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                    ".LinearLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget"
                                    ".LinearLayout["
                                    "2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                    "2]/android.widget.TextView")
el13.click()
el14 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/androidx"
                                    ".recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget"
                                    ".FrameLayout/android.widget.TextView")
el14.click()
time.sleep(2)
driver.back()

el10 = driver.find_element_by_id("tv.danmaku.bili:id/follow")
el10.click()
time.sleep(2)
el10.click()
time.sleep(2)
el11 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")
el11.click()
time.sleep(2)

el15 = driver.find_element_by_id("tv.danmaku.bili:id/frame1")
el15.click()
time.sleep(2)
el15.click()
time.sleep(2)
el16 = driver.find_element_by_id("tv.danmaku.bili:id/frame3")
el16.click()
time.sleep(2)
el17 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout["
                                    "1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                    "1]/android.widget.CheckBox")
el17.click()
time.sleep(2)
el18 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout["
                                    "2]/android.widget.LinearLayout/android.widget.TextView")
el18.click()
time.sleep(2)
el19 = driver.find_element_by_id("tv.danmaku.bili:id/frame3")
el19.click()
time.sleep(2)
el20 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout["
                                    "1]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                    "1]/android.widget.CheckBox")
el20.click()
time.sleep(2)
el21 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout["
                                    "2]/android.widget.LinearLayout/android.widget.TextView")
el21.click()
el22 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout["
                                    "2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget"
                                    ".HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout")
el22.click()
time.sleep(2)
el23 = driver.find_element_by_id("tv.danmaku.bili:id/input")
el23.click()
time.sleep(2)
el24 = driver.find_element_by_id("tv.danmaku.bili:id/edit")
el24.send_keys('6')
time.sleep(2)
el25 = driver.find_element_by_id("tv.danmaku.bili:id/send")
el25.click()
time.sleep(2)
el26 = driver.find_element_by_id("tv.danmaku.bili:id/new_danmaku_input")
el26.click()
time.sleep(2)
el27 = driver.find_element_by_id("tv.danmaku.bili:id/video_danmaku_input")
el27.send_keys('6')
time.sleep(2)
el28 = driver.find_element_by_id("tv.danmaku.bili:id/video_danmaku_send")
el28.click()
time.sleep(2)
el29 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
                                    ".ViewGroup/android.widget.FrameLayout["
                                    "2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget"
                                    ".HorizontalScrollView/android.widget.LinearLayout/android.widget.TextView")
el29.click()
time.sleep(2)
el30 = driver.find_element_by_id("tv.danmaku.bili:id/frame2")
el30.click()
TouchAction(driver).tap(x=528, y=1864).perform()
time.sleep(2)
driver.quit()
