#!/bin/sh

# ARP 테이블 가져오기
arp_table=$(arp -a)

# 새로운 줄 문자(\n)를 기준으로 각 줄을 배열로 저장
IFS=$'\n' read -rd '' -a lines <<< "$arp_table"

# IP와 MAC 주소만 추출하여 출력
echo "IP Address,MAC Address"
for line in "${lines[@]}"; do
    # 각 줄에서 IP 주소와 MAC 주소 추출
    ip_addr=$(echo "$line" | awk '{print $2}' | grep -oE '([0-9]{1,3}\.){3}[0-9]{1,3}')
    mac_addr=$(echo "$line" | awk '{print $4}' | grep -oE '([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}')

    # IP 주소와 MAC 주소가 모두 추출되었다면 출력
    if [[ -n "$ip_addr" && -n "$mac_addr" ]]; then
        echo "$ip_addr,$mac_addr"
    fi
done
