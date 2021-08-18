# Raspberry Pi Entrance Alarm

- I created this program in order to use a Raspberry Pi as a sort of entrance alarm.
- It uses a PIR motion sensor attached to the Pi as well as the app Pushover.
- The Raspberry Pi is mounted somewhere near an entrance.
- In a While loop, a variable (motion_detected) is constantly polled.
- When motion_detected == True, the Raspberry Pi sends an HTTP POST request to the Pushover API which triggers a push notification to be activated on your phone, alerting you that an entrance was detected.