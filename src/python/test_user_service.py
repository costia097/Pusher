import requests


def print_some():
    print "A"


class A():
    def __init__(self):
        pass
    print_some()
    r = requests.get('http://localhost:9090/getData', auth=('awdawd', '123'))
    assert r.status_code == 200
    r_json = r.json()
    print r_json
