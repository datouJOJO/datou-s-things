# Generated by Selenium IDE
import pytest
import time
import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import os

path = os.path.abspath('..')

class Test():
    def setup_method(self):
        self.driver = webdriver.Chrome()
        self.vars = {}

    def teardown_method(self):
        self.driver.quit()

    def test_(self):
        self.driver.get("http://app.xunjietupian.com/shape")
        self.driver.maximize_window()

        # 查找type为file的
        # 非图片的文件传入
        self.driver.find_element_by_xpath("//input[@type='file']").send_keys(
            path+
            '\\pic2.png')

        # 定位画布
        element = self.driver.find_element(By.CSS_SELECTOR, ".upper-canvas")
        # 鼠标拖拉才会显示实心矩形
        self.driver.find_element(By.CSS_SELECTOR, ".btn-shape:nth-child(3)").click()
        drawing = ActionChains(self.driver) \
            .click_and_hold(element) \
            .move_by_offset(0, 0) \
            .move_by_offset(200, 200) \
            .release()
        drawing.perform()
        # 选中元素
        self.driver.find_element(By.CSS_SELECTOR, ".btn-basic-select > img").click()
        drawing = ActionChains(self.driver) \
            .move_to_element_with_offset(element, 190, 190) \
            .click_and_hold() \
            .move_by_offset(0, 0) \
            .move_by_offset(350, 350) \
            .release()
        drawing.perform()

        time.sleep(1)
        # 重做
        self.driver.find_element(By.CSS_SELECTOR, ".btn-basic-reset > img").click()
        time.sleep(1)
        # 撤销
        self.driver.find_element(By.CSS_SELECTOR, ".btn-basic-undo > img").click()
        # 恢复
        time.sleep(1)
        self.driver.find_element(By.CSS_SELECTOR, ".btn-basic-redo > img").click()

        time.sleep(2)


def runTest():
    test = Test()
    test.setup_method()
    test.test_()
    test.teardown_method()


if __name__ == '__main__':
    runTest()
