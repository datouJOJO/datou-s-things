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
        self.driver.get("http://app.xunjietupian.com/text")
        self.driver.maximize_window()

        # 查找type为file的
        self.driver.find_element_by_xpath("//input[@type='file']").send_keys(
            path+
            '\\pic2.png')
        element = self.driver.find_element(By.CSS_SELECTOR, ".upper-canvas")

        actions = ActionChains(self.driver)
        actions.double_click(element).perform()
        actions.reset_actions()

        # 点击选择按钮
        self.driver.find_element(By.CSS_SELECTOR, ".btn-basic-select > img").click()

        drawing = ActionChains(self.driver) \
            .click_and_hold(element) \
            .move_by_offset(-150, -150) \
            .move_by_offset(150, 150) \
            .release()
        drawing.perform()

        # 点击删除
        self.driver.find_element(By.CSS_SELECTOR, ".btn-basic-delete > p").click()

        time.sleep(2)


def runTest():
    test = Test()
    test.setup_method()
    test.test_()
    test.teardown_method()


if __name__ == '__main__':
    runTest()
