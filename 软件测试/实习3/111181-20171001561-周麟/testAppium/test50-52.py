from appium import webdriver
import time

# 初始化参数

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
driver.find_element_by_id("expand_search").click()
# 输入“泰坦尼克号”
time.sleep(1)
driver.find_element_by_id("search_src_text").send_keys("泰坦尼克号")
# 键盘回车
driver.keyevent(66)
# # 因为它搜索完后就直接退出app了，看不到搜索结果页，所以我给了一个让他停下的方法
driver.back()
driver.back()
# 按索引搜索影视
el1 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget"
                                   ".HorizontalScrollView/android.widget.LinearLayout/android.view.ViewGroup[5]")
el1.click()
time.sleep(2)
el2 = driver.find_element_by_xpath(
    "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget"
    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view"
    ".ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android"
    ".widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget"
    ".FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]")
el2.click()
time.sleep(2)
el3 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android"
                                   ".widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android"
                                   ".widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout"
                                   "/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget"
                                   ".RecyclerView[1]/android.widget.FrameLayout[2]/android.widget.TextView")
el3.click()
time.sleep(2)
el4 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android"
                                   ".widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android"
                                   ".widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout"
                                   "/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget"
                                   ".RecyclerView[2]/android.widget.FrameLayout[2]/android.widget.TextView")
el4.click()
time.sleep(2)
el5 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android"
                                   ".widget.RelativeLayout/android.view.ViewGroup/android.widget.LinearLayout/android"
                                   ".widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout"
                                   "/androidx.recyclerview.widget.RecyclerView/androidx.recyclerview.widget"
                                   ".RecyclerView[3]/android.widget.FrameLayout[2]/android.widget.TextView")
el5.click()
time.sleep(2)
driver.back()
# 按索引搜索番剧
el1 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget"
                                   ".HorizontalScrollView/android.widget.LinearLayout/android.view.ViewGroup[2]")
el1.click()
time.sleep(2)
el6 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget"
                                   ".FrameLayout["
                                   "2]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView"
                                   "/android.widget.FrameLayout["
                                   "2]/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[1]")
el6.click()
time.sleep(2)
el7 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".RelativeLayout/android.view.ViewGroup["
                                   "2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget"
                                   ".LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget"
                                   ".RecyclerView/androidx.recyclerview.widget.RecyclerView["
                                   "1]/android.widget.FrameLayout[2]")
el7.click()
time.sleep(2)
el8 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".RelativeLayout/android.view.ViewGroup["
                                   "2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget"
                                   ".LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget"
                                   ".RecyclerView/androidx.recyclerview.widget.RecyclerView["
                                   "1]/android.widget.FrameLayout[2]/android.widget.TextView")
el8.click()
time.sleep(2)
el9 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                   ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                   ".RelativeLayout/android.view.ViewGroup["
                                   "2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget"
                                   ".LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget"
                                   ".RecyclerView/androidx.recyclerview.widget.RecyclerView["
                                   "2]/android.widget.FrameLayout[2]/android.widget.TextView")
el9.click()
time.sleep(2)
el10 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                    ".RelativeLayout/android.view.ViewGroup["
                                    "2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget"
                                    ".LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget"
                                    ".RecyclerView/androidx.recyclerview.widget.RecyclerView["
                                    "3]/android.widget.FrameLayout[2]/android.widget.TextView")
el10.click()
time.sleep(2)
el11 = driver.find_element_by_xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget"
                                    ".FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget"
                                    ".RelativeLayout/android.view.ViewGroup["
                                    "2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget"
                                    ".LinearLayout/android.widget.LinearLayout/androidx.recyclerview.widget"
                                    ".RecyclerView/androidx.recyclerview.widget.RecyclerView["
                                    "4]/android.widget.FrameLayout[2]/android.widget.TextView")
el11.click()
time.sleep(2)
driver.quit()
