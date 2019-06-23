package com.example.i80486.foodmanagement;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

public class CustomDialogFragment extends DialogFragment {

    // ダイアログが生成された時に呼ばれるメソッド ※必須
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // ダイアログ生成  AlertDialogのBuilderクラスを指定してインスタンス化します
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // タイトル設定
        builder.setTitle("ダイアログタイトル");
        // 表示する文章設定
        builder.setMessage("元画面に返す値を入力してください");

        // 入力フィールド作成
        final EditText editText = new EditText(getActivity());
        builder.setView(editText);

        // OKボタン作成
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            // editTextの内容を元画面に反映する
            // editTextから値を取得
            String returnValue = editText.getText().toString();
            // MainActivityのインスタンスを取得
            Main2Activity main2Activity = (Main2Activity) getActivity();
            main2Activity.setTextView(returnValue);
            }
        });

        // NGボタン作成
        builder.setNegativeButton("NG", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 何もしないで閉じる
            }
        });

        // dialogBulderを返す
        return builder.create();
    }
}
