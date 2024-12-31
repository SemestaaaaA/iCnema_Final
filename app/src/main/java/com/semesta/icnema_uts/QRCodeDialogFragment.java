package com.semesta.icnema_uts;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeDialogFragment extends DialogFragment {

    private static final String ARG_BOOKING_INFO = "booking_info";

    public static QRCodeDialogFragment newInstance(String bookingInfo) {
        QRCodeDialogFragment fragment = new QRCodeDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BOOKING_INFO, bookingInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_qr_code, container, false);

        ImageView qrCodeImage = view.findViewById(R.id.qr_code_image);

        String bookingInfo = getArguments().getString(ARG_BOOKING_INFO);
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(bookingInfo, BarcodeFormat.QR_CODE, 400, 400);
            qrCodeImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return view;
    }
}
