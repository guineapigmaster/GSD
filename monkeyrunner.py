from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

# Connects to the current device, returning a MonkeyDevice object
print 'before device'
device = MonkeyRunner.waitForConnection()
print 'done'

package = 'edu.cpp.rbkinney.gsd'
activity = 'edu.cpp.rbkinney.gsd.MainActivity'

# sets the name of the component to start
runComponent = package + '/' + activity

# Runs the component
device.startActivity(component=runComponent)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot0.png','png')
print 'shot0'

MonkeyRunner.sleep(2)
device.touch(400, 1500, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot1.png','png')
print 'shot1'

MonkeyRunner.sleep(2)
device.touch(300, 1100, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot2.png','png')
print 'shot2'

MonkeyRunner.sleep(2)
device.touch(300, 1200, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot3.png','png')
print 'shot3'

MonkeyRunner.sleep(2)
device.touch(800, 1600, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot4.png','png')
print 'shot4'

MonkeyRunner.sleep(2)
device.touch(800, 1600, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot5.png','png')
print 'shot5'

MonkeyRunner.sleep(2)
device.touch(200, 1600, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot6.png','png')
print 'shot6'

MonkeyRunner.sleep(2)
device.touch(200, 1600, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(2)
result = device.takeSnapshot()
result.writeToFile('/Users/Robert/Desktop/shot7.png','png')
print 'shot7'