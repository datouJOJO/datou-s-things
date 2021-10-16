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
time.sleep(6)
el1 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget"
                                   ".LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx"
                                   ".recyclerview.widget.RecyclerView/android.widget.RelativeLayout["
                                   "2]/android.widget.FrameLayout/android.widget.ImageView")
el1.click()
time.sleep(2)
driver.back()
TouchAction(driver).press(x=546, y=2039).move_to(x=552, y=419).release().perform()
time.sleep(2)
TouchAction(driver).press(x=546, y=217).move_to(x=537, y=1689).release().perform()
time.sleep(2)
TouchAction(driver).press(x=537, y=983).move_to(x=543, y=1762).release().perform()
time.sleep(2)
driver.quit()
