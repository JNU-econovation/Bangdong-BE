import subprocess
from collections import deque
from fastapi import FastAPI

app = FastAPI()

@app.get("/arp_table")
def get_arp_table():
    try:
        # 로컬 시스템에서 'arp -a' 명령어를 실행하여 ARP 테이블을 가져옵니다.
        # Windows 시스템인 경우 'arp -a'를 'arp -a -N [인터페이스 이름]'으로 변경해야 합니다.
        arp_table = subprocess.check_output(['./get_ip_mac.sh'])
        arp_table = arp_table.decode('utf-8')
        return {"arp_table": preprocess_ip_mac(arp_table)}
    except subprocess.CalledProcessError as e:
        return {"error": str(e)}

def preprocess_ip_mac(arp_table):
    response = deque()
    arp_table = arp_table.split("\n")
    print(arp_table)
    for address in arp_table:
        str_ = address.split(",")
        print(str_)
        try:
            response.append({"ip":str_[0],"mac":str_[1]})
        except:
            pass
    
    response.popleft()
    return response

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)

# ["IP Address,MAC Address","10.20.10.2,44:1e:98:22:b5:71","10.20.10.7,52:61:69:1f:dd:a1","10.20.10.45,de:4f:12:87:ed:e2","10.20.10.60,c4:35:d9:9d:9b:6c","10.20.10.190,12:65:52:dd:b2:33","10.20.10.191,b2:11:4c:ea:69:a5","10.20.10.194,4a:f5:e3:e2:cf:bd","10.20.10.230,68:3e:26:34:e9:69","10.20.11.21,e6:ab:17:eb:c7:45","10.20.11.85,74:a6:cd:ba:ca:8e","10.20.11.146,36:c0:d7:78:a8:59","10.20.11.147,aa:62:d6:1e:2e:ba","10.20.11.255,ff:ff:ff:ff:ff:ff","192.168.65.255,ff:ff:ff:ff:ff:ff",""]