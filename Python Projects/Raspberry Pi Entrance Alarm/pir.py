import time
import httplib
import urllib

from gpiozero import MotionSensor

class Notifier:
    
    def __init__(self, user_key, api_key):
        self.user_key = user_key
        self.api_key = api_key
 
    def send_notification(self, text):
        conn = httplib.HTTPSConnection("api.pushover.net:443")
        post_data = {'user': self.user_key, 'token': self.api_key, 'message': text}
        conn.request("POST", "/1/messages.json",
                     urllib.urlencode(post_data), {"Content-type": "application/x-www-form-urlencoded"})

def main():
	
	pir = MotionSensor(4)
	
	# the user_key and api_key below are left blank as they are specific to my phone
	# should this program be used for another device, a new user_key and api_key would be needed
	
	user_key = 
	api_key = 

	while True:
		if pir.motion_detected:
			notification = Notifier(user_key, api_key)
			notification.send_notification("Entrance detected!")
			time.sleep(30)


if __name__ == "__main__":
	main()
