package xinhocbong.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private float overallScore;
    private String mssv, name, gender, brithDate, familySituation, idHocBong, ngayNhan, soTien, tenToChuc;
}
