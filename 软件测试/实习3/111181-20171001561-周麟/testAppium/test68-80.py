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
b = driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget'
                                 '.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget'
                                 '.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget'
                                 '.FrameLayout['
                                 '3]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget'
                                 '.FrameLayout[5]')
b.click()
el1 = driver.find_element_by_id("tv.danmaku.bili:id/following_layout")
el1.click()
time.sleep(2)
driver.back()
time.sleep(2)
el2 = driver.find_element_by_id("tv.danmaku.bili:id/attention_layout")
el2.click()
time.sleep(2)
driver.back()
time.sleep(2)
el3 = driver.find_element_by_id("tv.danmaku.bili:id/fans_layout")
el3.click()
time.sleep(2)
driver.back()
time.sleep(2)
el4 = driver.find_element_by_id("tv.danmaku.bili:id/tv_go_to_space")
el4.click()
time.sleep(2)
driver.back()
time.sleep(2)
el5 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android"
                                   ".view.ViewGroup["
                                   "1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]")
el5.click()
time.sleep(2)
driver.back()
time.sleep(2)
el6 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android"
                                   ".view.ViewGroup["
                                   "1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup["
                                   "2]/android.widget.ImageView")
el6.click()
time.sleep(2)
driver.back()
time.sleep(2)
el7 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android"
                                   ".view.ViewGroup["
                                   "1]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup["
                                   "4]/android.widget.ImageView")
el7.click()
time.sleep(2)
driver.back()
time.sleep(2)
el8 = driver.find_element_by_id("tv.danmaku.bili:id/mine_theme_icon")
el8.click()
time.sleep(2)
TouchAction(driver).tap(x=371, y=739).perform()
time.sleep(2)
driver.back()
time.sleep(2)
el10 = driver.find_element_by_id("tv.danmaku.bili:id/mine_day_night_setting")
el10.click()
time.sleep(2)
TouchAction(driver).press(x=534, y=1373).move_to(x=561, y=860).release().perform()

el11 = driver.find_element_by_xpath(
    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget"
    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
    ".ViewGroup/android.widget.FrameLayout[1]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView"
    "/android.view.ViewGroup[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup["
    "4]/android.widget.ImageView[1]")
el11.click()
time.sleep(2)
TouchAction(driver).press(x=425, y=2121).move_to(x=453, y=742).release().perform()

el12 = driver.find_element_by_xpath(
    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget"
    ".FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget"
    ".LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout")
el12.click()
time.sleep(2)
el13 = driver.find_element_by_id("tv.danmaku.bili:id/common_dialog_positive_btn")
el13.click()
time.sleep(2)
driver.back()
time.sleep(2)
el14 = driver.find_element_by_id("tv.danmaku.bili:id/tv_login")
el14.click()
time.sleep(2)
el15 = driver.find_element_by_id("tv.danmaku.bili:id/tv_submit")
el15.click()
time.sleep(2)
el16 = driver.find_element_by_id("tv.danmaku.bili:id/tv_go_to_space")
el16.click()
time.sleep(2)
el17 = driver.find_element_by_id("tv.danmaku.bili:id/edit_info")
el17.click()
time.sleep(2)
el18 = driver.find_element_by_id("tv.danmaku.bili:id/signature")
el18.click()
time.sleep(2)
el19 = driver.find_element_by_id("tv.danmaku.bili:id/signature_edit")
el19.send_keys('臭宝笨比')
time.sleep(2)
el20 = driver.find_element_by_id("tv.danmaku.bili:id/menu_modify_save")
el20.click()
time.sleep(2)
driver.quit()
