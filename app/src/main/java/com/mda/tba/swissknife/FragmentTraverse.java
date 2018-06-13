package com.mda.tba.swissknife;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class FragmentTraverse extends Fragment implements OnClickListener {

    SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    final String RADIO_UNIT_SIZE_INDEX = "SAVED_RADIO_UNIT_SIZE_INDEX";
    final String RADIO_UNIT_AREA_INDEX = "SAVED_RADIO_UNIT_AREA_INDEX";
    final String RADIO_UNIT_VELOCITY_INDEX = "SAVED_RADIO_UNIT_VELOCITY_INDEX";
    final String RADIO_UNIT_VOLUME_INDEX = "SAVED_RADIO_UNIT_VOLUME_INDEX";
    final String RADIO_SYSTEM_INDEX = "SAVED_RADIO_SYSTEM_INDEX";
    // ------------------------------------------------------------------------
    DataGlobal dg = DataGlobal.getInstance();
    DataSwissknife dsk = DataSwissknife.getInstance();
    // ------------------------------------------------------------------------
    String[] unitSizeArray = null, unitAreaArray = null, unitVelocityArray = null, unitVolumeArray = null;

    String ductworkShape = dg.getDuctworkShape();

    int unitSizeCurrent = 0, unitAreaCurrent = 0, unitVelocityCurrent = 0, unitVolumeCurrent = 0;
    int methodCalculation = 0;

    // ------------------------------------------------------------------------
    private Button btnDuctShape;
    private TextView tvSize1, labelUnitSize;
    private TextView tvSize2;
    private TextView labelSeparator, labelFormulaArea;
    private TextView tvArea, labelUnitArea;
    private TextView tvVelocity, labelUnitVelocity;
    private TextView tvVolume, labelUnitVolume;
    private TextView tvDesign;
    private Button kbOne, kbTwo, kbThree, kbFour, kbFive, kbSix, kbSeven,
            kbEight, kbNine, kbZero, kbDot, kbOk;

    String newValue = "";
    Integer changedParametr = 0;
    Dialog popupKeyboard;
    Integer sizeText = 0;
    String orient = "land";


    public FragmentTraverse() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
//		setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_traverse, container, false);

        unitSizeArray = getActivity().getResources().getStringArray(R.array.u_size_text);
        unitAreaArray = getActivity().getResources().getStringArray(R.array.u_area_text);
        unitVelocityArray = getActivity().getResources().getStringArray(R.array.u_velocity_text);
        unitVolumeArray = getActivity().getResources().getStringArray(R.array.u_volume_text);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        methodCalculation = mSettings.getInt(RADIO_SYSTEM_INDEX, 0);

        labelUnitSize = (TextView) rootView.findViewById(R.id.labelUnitSize);

        tvSize1 = (TextView) rootView.findViewById(R.id.tvSize1);
        tvSize2 = (TextView) rootView.findViewById(R.id.tvSize2);
        labelSeparator = (TextView) rootView.findViewById(R.id.labelSeparator);
        labelFormulaArea = (TextView) rootView.findViewById(R.id.labelFormulaArea);

        tvArea = (TextView) rootView.findViewById(R.id.tvArea);
        labelUnitArea = (TextView) rootView.findViewById(R.id.labelUnitArea);

        tvVelocity = (TextView) rootView.findViewById(R.id.tvVelocity);
        labelUnitVelocity = (TextView) rootView.findViewById(R.id.labelUnitVelocity);

        tvVolume = (TextView) rootView.findViewById(R.id.tvVolume);
        labelUnitVolume = (TextView) rootView.findViewById(R.id.labelUnitVolume);

        tvDesign = (TextView) rootView.findViewById(R.id.tvDesign);

        btnDuctShape = (Button) rootView.findViewById(R.id.btnDuctShape);

        btnDuctShape.setOnClickListener(this);
        labelUnitSize.setOnClickListener(this);
        tvSize1.setOnClickListener(this);
        tvSize2.setOnClickListener(this);
        labelSeparator.setOnClickListener(this);
        labelUnitArea.setOnClickListener(this);
        tvVelocity.setOnClickListener(this);
        labelUnitVelocity.setOnClickListener(this);
        labelUnitVolume.setOnClickListener(this);
        tvDesign.setOnClickListener(this);

        // ------------------------------------------------------------------------
        popupKeyboard = new Dialog(getActivity());
        popupKeyboard.setTitle("Type new value");
        popupKeyboard.setContentView(R.layout.dialog_keyboard);
        // ------------------------------------------------------------------------
        kbOk = (Button) popupKeyboard.findViewById(R.id.kbOk);
        kbOne = (Button) popupKeyboard.findViewById(R.id.kbOne);
        kbTwo = (Button) popupKeyboard.findViewById(R.id.kbTwo);
        kbThree = (Button) popupKeyboard.findViewById(R.id.kbThree);
        kbFour = (Button) popupKeyboard.findViewById(R.id.kbFour);
        kbFive = (Button) popupKeyboard.findViewById(R.id.kbFive);
        kbSix = (Button) popupKeyboard.findViewById(R.id.kbSix);
        kbSeven = (Button) popupKeyboard.findViewById(R.id.kbSeven);
        kbEight = (Button) popupKeyboard.findViewById(R.id.kbEight);
        kbNine = (Button) popupKeyboard.findViewById(R.id.kbNine);
        kbDot = (Button) popupKeyboard.findViewById(R.id.kbDot);
        kbZero = (Button) popupKeyboard.findViewById(R.id.kbZero);
        kbOk.setOnClickListener(this);
        kbOne.setOnClickListener(this);
        kbTwo.setOnClickListener(this);
        kbThree.setOnClickListener(this);
        kbFour.setOnClickListener(this);
        kbFive.setOnClickListener(this);
        kbSix.setOnClickListener(this);
        kbSeven.setOnClickListener(this);
        kbEight.setOnClickListener(this);
        kbNine.setOnClickListener(this);
        kbDot.setOnClickListener(this);
        kbZero.setOnClickListener(this);
        // ------------------------------------------------------------------------
        selectDuctWork(ductworkShape);
        reCalculate();

        return rootView;
    }


    private void SavePreferences(String key, int value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDuctShape:
                if (ductworkShape.equals("rect")) {
                    ductworkShape = "circ";
                } else if (ductworkShape.equals("circ")) {
                    ductworkShape = "oval";
                } else {
                    ductworkShape = "rect";
                }
                dg.setDuctworkShape(ductworkShape);
                selectDuctWork(ductworkShape);
                reCalculate();
                break;
            case R.id.tvSize1:
                changedParametr = v.getId();
                popupKeyboard.show();
                popupKeyboard.setTitle(getResources().getString(R.string.textDialogSize) + " 0 "
                        + unitSizeArray[mSettings.getInt(RADIO_UNIT_SIZE_INDEX, 0)]);
                newValue = "";
                break;
            case R.id.tvSize2:
                changedParametr = v.getId();
                popupKeyboard.show();
                popupKeyboard.setTitle(getResources().getString(R.string.textDialogSize) + " 0 "
                        + unitSizeArray[mSettings.getInt(RADIO_UNIT_SIZE_INDEX, 0)]);
                newValue = "";
                break;
            case R.id.tvVelocity:
                changedParametr = v.getId();
                popupKeyboard.show();
                popupKeyboard.setTitle(getResources().getString(R.string.textDialogVelocity) + " 0 "
                        + unitVelocityArray[mSettings.getInt(RADIO_UNIT_VELOCITY_INDEX, 0)]);
                newValue = "";
                break;
            case R.id.tvDesign:
                changedParametr = v.getId();
                popupKeyboard.show();
                popupKeyboard.setTitle(getResources().getString(R.string.textDialogRate) + " 0 "
                        + unitVolumeArray[mSettings.getInt(RADIO_UNIT_VOLUME_INDEX, 0)]);
                newValue = "";
                break;
            case R.id.kbOk:
                popupKeyboard.dismiss();
                if (newValue.equals(""))
                    newValue = "0";
                switch (changedParametr) {
                    case R.id.tvSize1:
                        dsk.setSize1(newValue, unitSizeCurrent);
                        break;
                    case R.id.tvSize2:
                        dsk.setSize2(newValue, unitSizeCurrent);
                        break;
                    case R.id.tvVelocity:
                        dsk.setVelocity(newValue, unitVelocityCurrent);
                        break;
                    case R.id.tvDesign:
                        dsk.setDesign(newValue, unitVolumeCurrent);
                        break;
                    default:
                }
                reCalculate();
                break;
            case R.id.kbOne:
                newValue += "1";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbTwo:
                newValue += "2";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbThree:
                newValue += "3";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbFour:
                newValue += "4";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbFive:
                newValue += "5";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbSix:
                newValue += "6";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbSeven:
                newValue += "7";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbEight:
                newValue += "8";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbNine:
                newValue += "9";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbDot:
                if (!newValue.endsWith(".")) newValue += ".";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.kbZero:
                newValue += "0";
                pressNewKeyboard(changedParametr);
                break;
            case R.id.labelUnitSize:
                switch (unitSizeCurrent) {
                    case 0:
                        unitSizeCurrent = 1;
                        break;
                    case 1:
                        unitSizeCurrent = 0;
                        break;
                    default:
                }
                SavePreferences(RADIO_UNIT_SIZE_INDEX, unitSizeCurrent);
                reCalculate();
                break;
            case R.id.labelUnitArea:
                switch (unitAreaCurrent) {
                    case 0:
                        unitAreaCurrent = 1;
                        break;
                    case 1:
                        unitAreaCurrent = 0;
                        break;
                    default:
                }
                SavePreferences(RADIO_UNIT_AREA_INDEX, unitAreaCurrent);
                reCalculate();
                break;
            case R.id.labelUnitVelocity:
                switch (unitVelocityCurrent) {
                    case 0:
                        unitVelocityCurrent = 1;
                        break;
                    case 1:
                        unitVelocityCurrent = 0;
                        break;
                    default:
                }
                SavePreferences(RADIO_UNIT_VELOCITY_INDEX, unitVelocityCurrent);
                reCalculate();
                break;
            case R.id.labelUnitVolume:
                switch (unitVolumeCurrent) {
                    case 0:
                        unitVolumeCurrent = 1;
                        break;
                    case 1:
                        unitVolumeCurrent = 2;
                        break;
                    case 2:
                        unitVolumeCurrent = 0;
                        break;
                    default:
                }
                SavePreferences(RADIO_UNIT_VOLUME_INDEX, unitVolumeCurrent);
                reCalculate();
                break;
            default:
        }
    }


    // ==========================================================================================
    // в зависимости от значения ductworkShape показывает/скрывает textView и пересчитывает,
    // а затем сохраняет все значения
    private void selectDuctWork(String ductworkShape) {
        dg.setDuctworkShape(ductworkShape);
        if (ductworkShape.equals("circ")) {
            btnDuctShape.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_circ));
            tvSize1.setVisibility(TextView.INVISIBLE);
            labelSeparator.setText(getResources().getString(R.string.textLabelDia));
            labelFormulaArea.setText(getResources().getString(R.string.formula_area_circ));
            if (dsk.getSize2(unitSizeCurrent).equals("")) { tvSize2.setText(getResources().getString(R.string.labelDiameter));}
        } else if (ductworkShape.equals("oval")) {
            btnDuctShape.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_oval));
            tvSize1.setVisibility(TextView.VISIBLE);
            labelSeparator.setText(getResources().getString(R.string.textLabelCross));
            labelFormulaArea.setText(getResources().getString(R.string.formula_area_oval));
            if (dsk.getSize1(unitSizeCurrent).equals("")) { tvSize1.setText(getResources().getString(R.string.labelWidth));}
            if (dsk.getSize2(unitSizeCurrent).equals("")) { tvSize2.setText(getResources().getString(R.string.labelHeight));}
        } else {
            btnDuctShape.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_rect));
            tvSize1.setVisibility(TextView.VISIBLE);
            labelSeparator.setText(getResources().getString(R.string.textLabelCross));
            labelFormulaArea.setText(getResources().getString(R.string.formula_area_rect));
            if (dsk.getSize1(unitSizeCurrent).equals("")) { tvSize1.setText(getResources().getString(R.string.labelWidth));}
            if (dsk.getSize2(unitSizeCurrent).equals("")) { tvSize2.setText(getResources().getString(R.string.labelHeight));}
        }
    }


    // ==========================================================================================
    // показывает вводимое значение в заглавии pop-up клавиатуры
    private boolean pressNewKeyboard(Integer changedP) {
        switch (changedP) {
            case R.id.tvSize1:
                popupKeyboard.setTitle(getActivity().getResources().getString(R.string.textDialogSize) + " " +
                        newValue + " " + unitSizeArray[unitSizeCurrent]);
                break;
            case R.id.tvSize2:
                popupKeyboard.setTitle(getActivity().getResources().getString(R.string.textDialogSize) + " " +
                        newValue + " " + unitSizeArray[unitSizeCurrent]);
                break;
            case R.id.tvVelocity:
                popupKeyboard.setTitle(getActivity().getResources().getString(R.string.textDialogVelocity) + " " +
                        newValue + " " + unitVelocityArray[unitVelocityCurrent]);
                break;
            case R.id.tvDesign:
                popupKeyboard.setTitle(getActivity().getResources().getString(R.string.textDialogRate) + " " +
                        newValue + " " + unitVolumeArray[unitVolumeCurrent]);
                break;
            default:
                break;
        }
        return true;
    }


    // ==========================================================================================
    // перерасчитвает и отображает
    private boolean reCalculate() {
        unitSizeCurrent = mSettings.getInt(RADIO_UNIT_SIZE_INDEX, 0);
        unitAreaCurrent = mSettings.getInt(RADIO_UNIT_AREA_INDEX, 0);
        unitVelocityCurrent = mSettings.getInt(RADIO_UNIT_VELOCITY_INDEX, 0);
        unitVolumeCurrent = mSettings.getInt(RADIO_UNIT_VOLUME_INDEX, 0);
        tvSize1.setText(dsk.getSize1(unitSizeCurrent));
        tvSize2.setText(dsk.getSize2(unitSizeCurrent));
        tvArea.setText(dsk.getArea(unitAreaCurrent, ductworkShape));
        tvVelocity.setText(dsk.getVelocity(unitVelocityCurrent));
        tvVolume.setText(dsk.getVolume(unitVolumeCurrent, ductworkShape));
        tvDesign.setText(dsk.getDesign(unitVolumeCurrent, ductworkShape,
                getResources().getString(R.string.pretext_ratio),
                unitVolumeArray[unitVolumeCurrent]));
        labelUnitSize.setText(unitSizeArray[unitSizeCurrent]);
        labelUnitArea.setText(unitAreaArray[unitAreaCurrent]);
        labelUnitVelocity.setText(unitVelocityArray[unitVelocityCurrent]);
        labelUnitVolume.setText(unitVolumeArray[unitVolumeCurrent]);
        return true;
    }

}