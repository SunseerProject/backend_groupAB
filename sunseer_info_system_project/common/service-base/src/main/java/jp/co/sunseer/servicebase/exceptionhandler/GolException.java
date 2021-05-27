package jp.co.sunseer.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GolException extends RuntimeException {
    private Integer code;//ステータスコード
    private String msg;//異常メッセージ
}
