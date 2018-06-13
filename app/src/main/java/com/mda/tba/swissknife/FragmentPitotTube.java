package com.mda.tba.swissknife;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


public class FragmentPitotTube extends Fragment implements View.OnClickListener {
    SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    final String RADIO_UNIT_SIZE_INDEX = "SAVED_RADIO_UNIT_SIZE_INDEX";
    final String RADIO_SYSTEM_INDEX = "SAVED_RADIO_SYSTEM_INDEX";
    int methodCalculation = 0;
    Button btnMethod;
    // ------------------------------------------------------------------------
    DataGlobal dg = DataGlobal.getInstance();
    DataSwissknife dsk = DataSwissknife.getInstance();
    String[] unitSizeArray = null;
    // ------------------------------------------------------------------------
    long size1, size2;
    String ductworkShape;
    View rootView;

    int unitSizeCurrent = 0;
    int setPoint4 = 100, setPoint6 = 150, setPoint10 = 250, setPoint12 = 300, setPoint15 = 380,
            setPoint24 = 610, setPoint30 = 750, setPoint35 = 890, setPoint48 = 1220,
            setPoint63 = 1600, setPoint80 = 2030, setPoint99 = 2515;

    public FragmentPitotTube() {
        // Empty constructor required for fragment subclasses
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pitot_tube, container, false);

        unitSizeArray = getActivity().getResources().getStringArray(R.array.u_size_text);

        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        unitSizeCurrent = mSettings.getInt(RADIO_UNIT_SIZE_INDEX, 0);
        methodCalculation = mSettings.getInt(RADIO_SYSTEM_INDEX, 0);

        size1 = dsk.get_Size1();
        size2 = dsk.get_Size2();

        ductworkShape = dg.getDuctworkShape();
        ImageView imageShape = rootView.findViewById(R.id.imageShape);

        if (ductworkShape.equals("rect")) {
            imageShape.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.rect));
        } else if (ductworkShape.equals("circ")) {
            imageShape.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.circ));
        } else {
            imageShape.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.oval));
        }

        btnMethod = rootView.findViewById(R.id.btnMethod);
        ShowBtnMethod(methodCalculation);
        btnMethod.setOnClickListener(this);

        makeLayouts(size1, size2, methodCalculation, ductworkShape, rootView);

        return rootView;
    }




    @Override
    public void onClick(View v) {
        switch (methodCalculation) {
            case 0:
                methodCalculation = 1;
                break;
            case 1:
                methodCalculation = 0;
                break;
            default:
        }
        SavePreferences(RADIO_SYSTEM_INDEX, methodCalculation);
        ShowBtnMethod(methodCalculation);
        makeLayouts(size1, size2, methodCalculation, ductworkShape, rootView);
    }



    private void SavePreferences(String key, int value) {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(key, value);
        editor.apply();
    }



    private void makeLayouts(long size1, long size2, int method, String shape, View v) {

        LinearLayout layLeft = v.findViewById(R.id.layLeft);
        LinearLayout layCentre = v.findViewById(R.id.layCentre);
        LinearLayout layRight = v.findViewById(R.id.layRight);

        layLeft.removeAllViews();
        layCentre.removeAllViews();
        layRight.removeAllViews();
/*
        LinearLayout.LayoutParams par = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        par.width = 0;
        par.weight = 1;
        par.gravity = Gravity.CENTER_VERTICAL + Gravity.RIGHT;
        layLeft.setLayoutParams(par);
        layCentre.setLayoutParams(par);
        layRight.setLayoutParams(par);
*/
        if (method == 0) { //.equals("logt")) {
//--            if (orientation.equals("port")) {
            if (shape.equals("circ")) {
                if (size2 <= setPoint10) {
//                    drawSection("circ", "any", new float[]{3.2f, 13.5f, 32.1f, 67.9f, 86.5f, 96.8f});
                    makeCell(size2, 3.2f, "green", layCentre);
                    makeCell(size2, 13.5f, "green", layCentre);
                    makeCell(size2, 32.1f, "green", layCentre);
                    makeCell(size2, 67.9f, "green", layCentre);
                    makeCell(size2, 86.5f, "green", layCentre);
                    makeCell(size2, 96.8f, "green", layCentre);
                } else if (size2 > setPoint10) {
//                    drawSection("circ", "any", new float[]{1.9f, 7.7f, 15.3f, 21.7f, 36.1f, 63.9f, 78.3f, 84.7f, 92.3f, 98.1f});
                    makeCell(size2, 1.9f, "green", layCentre);
                    makeCell(size2, 7.7f, "green", layCentre);
                    makeCell(size2, 15.3f, "green", layCentre);
                    makeCell(size2, 21.7f, "green", layCentre);
                    makeCell(size2, 36.1f, "green", layCentre);
                    makeCell(size2, 63.9f, "green", layCentre);
                    makeCell(size2, 78.3f, "green", layCentre);
                    makeCell(size2, 84.7f, "green", layCentre);
                    makeCell(size2, 92.3f, "green", layCentre);
                    makeCell(size2, 98.1f, "green", layCentre);
                }
            } else if (shape.equals("rect")) {
                if (size1 <= setPoint12) {
//                    drawSection("rect", "hor", new float[]{6.4f, 50.0f, 93.6f});
                    makeCell(size1, 6.4f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 93.6f, "red", layLeft);
                } else if (size1 < setPoint30) {
//                    drawSection("rect", "hor", new float[]{7.4f, 28.8f, 50.0f, 71.2f, 92.6f});
                    makeCell(size1, 7.4f, "red", layLeft);
                    makeCell(size1, 28.8f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 71.2f, "red", layLeft);
                    makeCell(size1, 92.6f, "red", layLeft);
                } else if (size1 <= setPoint63) {
//                    drawSection("rect", "hor", new float[]{6.1f, 23.5f, 43.7f, 56.3f, 76.5f, 93.9f});
                    makeCell(size1, 6.1f, "red", layLeft);
                    makeCell(size1, 23.5f, "red", layLeft);
                    makeCell(size1, 43.7f, "red", layLeft);
                    makeCell(size1, 56.3f, "red", layLeft);
                    makeCell(size1, 76.5f, "red", layLeft);
                    makeCell(size1, 93.9f, "red", layLeft);
                } else if (size1 > setPoint63) {
//                    drawSection("rect", "hor", new float[]{5.3f, 20.3f, 36.6f, 50.0f, 63.4f, 79.7f, 94.7f});
                    makeCell(size1, 5.3f, "red", layLeft);
                    makeCell(size1, 20.3f, "red", layLeft);
                    makeCell(size1, 36.6f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 63.4f, "red", layLeft);
                    makeCell(size1, 79.7f, "red", layLeft);
                    makeCell(size1, 94.7f, "red", layLeft);
                }
                if (size2 <= setPoint12) {
//                    drawSection("rect", "ver", new float[]{6.4f, 50.0f, 93.6f});
                    makeCell(size2, 6.4f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 93.6f, "blue", layRight);
                } else if (size2 < setPoint30) {
//                    drawSection("rect", "ver", new float[]{7.4f, 28.8f, 50.0f, 71.2f, 92.6f});
                    makeCell(size2, 7.4f, "blue", layRight);
                    makeCell(size2, 28.8f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 71.2f, "blue", layRight);
                    makeCell(size2, 92.6f, "blue", layRight);
                } else if (size2 <= setPoint63) {
//                    drawSection("rect", "ver", new float[]{6.1f, 23.5f, 43.7f, 56.3f, 76.5f, 93.9f});
                    makeCell(size2, 6.1f, "blue", layRight);
                    makeCell(size2, 23.5f, "blue", layRight);
                    makeCell(size2, 43.7f, "blue", layRight);
                    makeCell(size2, 56.3f, "blue", layRight);
                    makeCell(size2, 76.5f, "blue", layRight);
                    makeCell(size2, 93.9f, "blue", layRight);
                } else if (size2 > setPoint63) {
//                    drawSection("rect", "ver", new float[]{5.3f, 20.3f, 36.6f, 50.0f, 63.4f, 79.7f, 94.7f});
                    makeCell(size2, 5.3f, "blue", layRight);
                    makeCell(size2, 20.3f, "blue", layRight);
                    makeCell(size2, 36.6f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 63.4f, "blue", layRight);
                    makeCell(size2, 79.7f, "blue", layRight);
                    makeCell(size2, 94.7f, "blue", layRight);
                }
            } else if (shape.equals("oval")) {
                if ((size1 - size2) < size2) {
                    if (size1 <= setPoint10) {
//                        drawSection("oval_round", "hor", new float[]{3.2f, 13.5f, 32.1f, 67.9f, 86.5f, 96.8f});
                        makeCell(size1, 3.2f, "red", layLeft);
                        makeCell(size1, 13.5f, "red", layLeft);
                        makeCell(size1, 32.1f, "red", layLeft);
                        makeCell(size1, 67.9f, "red", layLeft);
                        makeCell(size1, 86.5f, "red", layLeft);
                        makeCell(size1, 96.8f, "red", layLeft);
                    } else if (size1 > setPoint10) {
//                        drawSection("oval_round", "hor", new float[]{1.9f, 7.7f, 15.3f, 21.7f, 36.1f, 63.9f, 78.3f, 84.7f, 92.3f, 98.1f});
                        makeCell(size1, 1.9f, "red", layLeft);
                        makeCell(size1, 7.7f, "red", layLeft);
                        makeCell(size1, 15.3f, "red", layLeft);
                        makeCell(size1, 21.7f, "red", layLeft);
                        makeCell(size1, 36.1f, "red", layLeft);
                        makeCell(size1, 63.9f, "red", layLeft);
                        makeCell(size1, 78.3f, "red", layLeft);
                        makeCell(size1, 84.7f, "red", layLeft);
                        makeCell(size1, 92.3f, "red", layLeft);
                        makeCell(size1, 98.1f, "red", layLeft);
                    }
                    if (size2 <= setPoint10) {
//                        drawSection("oval_round", "ver", new float[]{3.2f, 13.5f, 32.1f, 67.9f, 86.5f, 96.8f});
                        makeCell(size2, 3.2f, "blue", layRight);
                        makeCell(size2, 13.5f, "blue", layRight);
                        makeCell(size2, 32.1f, "blue", layRight);
                        makeCell(size2, 67.9f, "blue", layRight);
                        makeCell(size2, 86.5f, "blue", layRight);
                        makeCell(size2, 96.8f, "blue", layRight);
                    } else if (size2 > setPoint10) {
//                        drawSection("oval_round", "ver", new float[]{1.9f, 7.7f, 15.3f, 21.7f, 36.1f, 63.9f, 78.3f, 84.7f, 92.3f, 98.1f});
                        makeCell(size2, 1.9f, "blue", layRight);
                        makeCell(size2, 7.7f, "blue", layRight);
                        makeCell(size2, 15.3f, "blue", layRight);
                        makeCell(size2, 21.7f, "blue", layRight);
                        makeCell(size2, 36.1f, "blue", layRight);
                        makeCell(size2, 63.9f, "blue", layRight);
                        makeCell(size2, 78.3f, "blue", layRight);
                        makeCell(size2, 84.7f, "blue", layRight);
                        makeCell(size2, 92.3f, "blue", layRight);
                        makeCell(size2, 98.1f, "blue", layRight);
                    }
                } else if ((size1 - size2) >= size2) {
                    long size0 = size1 - size2;
                    if (size0 <= setPoint12) {
//                        drawSection("oval_narrow", "hor", new float[]{6.4f, 50.0f, 93.6f});
                        makeCell(size0, 6.4f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 93.6f, "red", layLeft);
                    } else if (size0 < setPoint30) {
//                        drawSection("oval_narrow", "hor", new float[]{7.4f, 28.8f, 50.0f, 71.2f, 92.6f});
                        makeCell(size0, 7.4f, "red", layLeft);
                        makeCell(size0, 28.8f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 71.2f, "red", layLeft);
                        makeCell(size0, 92.6f, "red", layLeft);
                    } else if (size0 <= setPoint63) {
//                        drawSection("oval_narrow", "hor", new float[]{6.1f, 23.5f, 43.7f, 56.3f, 76.5f, 93.9f});
                        makeCell(size0, 6.1f, "red", layLeft);
                        makeCell(size0, 23.5f, "red", layLeft);
                        makeCell(size0, 43.7f, "red", layLeft);
                        makeCell(size0, 56.3f, "red", layLeft);
                        makeCell(size0, 76.5f, "red", layLeft);
                        makeCell(size0, 93.9f, "red", layLeft);
                    } else if (size0 > setPoint63) {
//                        drawSection("oval_narrow", "hor", new float[]{5.3f, 20.3f, 36.6f, 50.0f, 63.4f, 79.7f, 94.7f});
                        makeCell(size0, 5.3f, "red", layLeft);
                        makeCell(size0, 20.3f, "red", layLeft);
                        makeCell(size0, 36.6f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 63.4f, "red", layLeft);
                        makeCell(size0, 79.7f, "red", layLeft);
                        makeCell(size0, 94.7f, "red", layLeft);
                    }
                    if (size2 <= setPoint12) {
//                        drawSection("oval_narrow", "ver", new float[]{6.4f, 50.0f, 93.6f});
                        makeCell(size2, 6.4f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 93.6f, "blue", layRight);
                    } else if (size2 < setPoint30) {
//                        drawSection("oval_narrow", "ver", new float[]{7.4f, 28.8f, 50.0f, 71.2f, 92.6f});
                        makeCell(size2, 7.4f, "blue", layRight);
                        makeCell(size2, 28.8f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 71.2f, "blue", layRight);
                        makeCell(size2, 92.6f, "blue", layRight);
                    } else if (size2 <= setPoint63) {
//                        drawSection("oval_narrow", "ver", new float[]{6.1f, 23.5f, 43.7f, 56.3f, 76.5f, 93.9f});
                        makeCell(size2, 6.1f, "blue", layRight);
                        makeCell(size2, 23.5f, "blue", layRight);
                        makeCell(size2, 43.7f, "blue", layRight);
                        makeCell(size2, 56.3f, "blue", layRight);
                        makeCell(size2, 76.5f, "blue", layRight);
                        makeCell(size2, 93.9f, "blue", layRight);
                    } else if (size2 > setPoint63) {
//                        drawSection("oval_narrow", "ver", new float[]{5.3f, 20.3f, 36.6f, 50.0f, 63.4f, 79.7f, 94.7f});
                        makeCell(size2, 5.3f, "blue", layRight);
                        makeCell(size2, 20.3f, "blue", layRight);
                        makeCell(size2, 36.6f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 63.4f, "blue", layRight);
                        makeCell(size2, 79.7f, "blue", layRight);
                        makeCell(size2, 94.7f, "blue", layRight);
                    }
                }
            }
//--            }
        } else if (method == 1) { //.equals("area")) {
            if (shape.equals("circ")) {
                if (size2 <= setPoint10) {
                    makeCell(size2, 4.3f, "green", layCentre);
                    makeCell(size2, 14.7f, "green", layCentre);
                    makeCell(size2, 29.6f, "green", layCentre);
                    makeCell(size2, 70.4f, "green", layCentre);
                    makeCell(size2, 85.3f, "green", layCentre);
                    makeCell(size2, 95.7f, "green", layCentre);
                } else if (size2 > setPoint10) {
                    makeCell(size2, 2.6f, "green", layCentre);
                    makeCell(size2, 8.2f, "green", layCentre);
                    makeCell(size2, 14.6f, "green", layCentre);
                    makeCell(size2, 22.6f, "green", layCentre);
                    makeCell(size2, 34.2f, "green", layCentre);
                    makeCell(size2, 65.8f, "green", layCentre);
                    makeCell(size2, 77.4f, "green", layCentre);
                    makeCell(size2, 85.4f, "green", layCentre);
                    makeCell(size2, 91.8f, "green", layCentre);
                    makeCell(size2, 97.4f, "green", layCentre);
                }
            } else if (shape.equals("rect")) {
                if (size1 <= setPoint6) {
                    makeCell(size1, 25.0f, "red", layLeft);
                    makeCell(size1, 75.0f, "red", layLeft);
                } else if (size1 <= setPoint15) {
                    makeCell(size1, 16.7f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 83.3f, "red", layLeft);
                } else if (size1 <= setPoint24) {
                    makeCell(size1, 12.5f, "red", layLeft);
                    makeCell(size1, 37.5f, "red", layLeft);
                    makeCell(size1, 62.5f, "red", layLeft);
                    makeCell(size1, 87.5f, "red", layLeft);
                } else if (size1 <= setPoint35) {
                    makeCell(size1, 10.0f, "red", layLeft);
                    makeCell(size1, 30.0f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 70.0f, "red", layLeft);
                    makeCell(size1, 90.0f, "red", layLeft);
                } else if (size1 <= setPoint48) {
                    makeCell(size1, 8.4f, "red", layLeft);
                    makeCell(size1, 25.1f, "red", layLeft);
                    makeCell(size1, 41.7f, "red", layLeft);
                    makeCell(size1, 58.3f, "red", layLeft);
                    makeCell(size1, 74.9f, "red", layLeft);
                    makeCell(size1, 91.6f, "red", layLeft);
                } else if (size1 <= setPoint63) {
                    makeCell(size1, 7.2f, "red", layLeft);
                    makeCell(size1, 21.5f, "red", layLeft);
                    makeCell(size1, 35.7f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 64.3f, "red", layLeft);
                    makeCell(size1, 78.5f, "red", layLeft);
                    makeCell(size1, 92.8f, "red", layLeft);
                } else if (size1 <= setPoint80) {
                    makeCell(size1, 6.25f, "red", layLeft);
                    makeCell(size1, 18.75f, "red", layLeft);
                    makeCell(size1, 31.25f, "red", layLeft);
                    makeCell(size1, 43.75f, "red", layLeft);
                    makeCell(size1, 56.25f, "red", layLeft);
                    makeCell(size1, 68.75f, "red", layLeft);
                    makeCell(size1, 81.25f, "red", layLeft);
                    makeCell(size1, 93.75f, "red", layLeft);
                } else if (size1 <= setPoint99) {
                    makeCell(size1, 5.55f, "red", layLeft);
                    makeCell(size1, 16.65f, "red", layLeft);
                    makeCell(size1, 27.75f, "red", layLeft);
                    makeCell(size1, 38.85f, "red", layLeft);
                    makeCell(size1, 50.0f, "red", layLeft);
                    makeCell(size1, 61.15f, "red", layLeft);
                    makeCell(size1, 72.25f, "red", layLeft);
                    makeCell(size1, 83.35f, "red", layLeft);
                    makeCell(size1, 94.45f, "red", layLeft);
                } else if (size1 > setPoint99) {
                    makeCell(size1, 5.0f, "red", layLeft);
                    makeCell(size1, 15.0f, "red", layLeft);
                    makeCell(size1, 25.0f, "red", layLeft);
                    makeCell(size1, 35.0f, "red", layLeft);
                    makeCell(size1, 45.0f, "red", layLeft);
                    makeCell(size1, 55.0f, "red", layLeft);
                    makeCell(size1, 65.0f, "red", layLeft);
                    makeCell(size1, 75.0f, "red", layLeft);
                    makeCell(size1, 85.0f, "red", layLeft);
                    makeCell(size1, 95.0f, "red", layLeft);
                }
                if (size2 <= setPoint6) {
                    makeCell(size2, 25.0f, "blue", layRight);
                    makeCell(size2, 75.0f, "blue", layRight);
                } else if (size2 <= setPoint15) {
                    makeCell(size2, 16.7f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 83.3f, "blue", layRight);
                } else if (size2 <= setPoint24) {
                    makeCell(size2, 12.5f, "blue", layRight);
                    makeCell(size2, 37.5f, "blue", layRight);
                    makeCell(size2, 62.5f, "blue", layRight);
                    makeCell(size2, 87.5f, "blue", layRight);
                } else if (size2 <= setPoint35) {
                    makeCell(size2, 10.0f, "blue", layRight);
                    makeCell(size2, 30.0f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 70.0f, "blue", layRight);
                    makeCell(size2, 90.0f, "blue", layRight);
                } else if (size2 <= setPoint48) {
                    makeCell(size2, 8.4f, "blue", layRight);
                    makeCell(size2, 25.1f, "blue", layRight);
                    makeCell(size2, 41.7f, "blue", layRight);
                    makeCell(size2, 58.3f, "blue", layRight);
                    makeCell(size2, 74.9f, "blue", layRight);
                    makeCell(size2, 91.6f, "blue", layRight);
                } else if (size2 <= setPoint63) {
                    makeCell(size2, 7.2f, "blue", layRight);
                    makeCell(size2, 21.5f, "blue", layRight);
                    makeCell(size2, 35.7f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 64.3f, "blue", layRight);
                    makeCell(size2, 78.5f, "blue", layRight);
                    makeCell(size2, 92.8f, "blue", layRight);
                } else if (size2 <= setPoint80) {
                    makeCell(size2, 6.25f, "blue", layRight);
                    makeCell(size2, 18.75f, "blue", layRight);
                    makeCell(size2, 31.25f, "blue", layRight);
                    makeCell(size2, 43.75f, "blue", layRight);
                    makeCell(size2, 56.25f, "blue", layRight);
                    makeCell(size2, 68.75f, "blue", layRight);
                    makeCell(size2, 81.25f, "blue", layRight);
                    makeCell(size2, 93.75f, "blue", layRight);
                } else if (size2 <= setPoint99) {
                    makeCell(size2, 5.55f, "blue", layRight);
                    makeCell(size2, 16.65f, "blue", layRight);
                    makeCell(size2, 27.75f, "blue", layRight);
                    makeCell(size2, 38.85f, "blue", layRight);
                    makeCell(size2, 50.0f, "blue", layRight);
                    makeCell(size2, 61.15f, "blue", layRight);
                    makeCell(size2, 72.25f, "blue", layRight);
                    makeCell(size2, 83.35f, "blue", layRight);
                    makeCell(size2, 94.45f, "blue", layRight);
                } else if (size2 > setPoint99) {
                    makeCell(size2, 5.0f, "blue", layRight);
                    makeCell(size2, 15.0f, "blue", layRight);
                    makeCell(size2, 25.0f, "blue", layRight);
                    makeCell(size2, 35.0f, "blue", layRight);
                    makeCell(size2, 45.0f, "blue", layRight);
                    makeCell(size2, 55.0f, "blue", layRight);
                    makeCell(size2, 65.0f, "blue", layRight);
                    makeCell(size2, 75.0f, "blue", layRight);
                    makeCell(size2, 85.0f, "blue", layRight);
                    makeCell(size2, 95.0f, "blue", layRight);
                }
            } else if (shape.equals("oval")) {
                if ((size1 - size2) < size2) {
                    if (size1 <= setPoint10) {
                        makeCell(size1, 4.3f, "red", layLeft);
                        makeCell(size1, 14.7f, "red", layLeft);
                        makeCell(size1, 29.6f, "red", layLeft);
                        makeCell(size1, 70.4f, "red", layLeft);
                        makeCell(size1, 85.3f, "red", layLeft);
                        makeCell(size1, 95.7f, "red", layLeft);
                    } else if (size1 > setPoint10) {
                        makeCell(size1, 2.6f, "red", layLeft);
                        makeCell(size1, 8.2f, "red", layLeft);
                        makeCell(size1, 14.6f, "red", layLeft);
                        makeCell(size1, 22.6f, "red", layLeft);
                        makeCell(size1, 34.2f, "red", layLeft);
                        makeCell(size1, 65.8f, "red", layLeft);
                        makeCell(size1, 77.4f, "red", layLeft);
                        makeCell(size1, 85.4f, "red", layLeft);
                        makeCell(size1, 91.8f, "red", layLeft);
                        makeCell(size1, 97.4f, "red", layLeft);
                    }
                    if (size2 <= setPoint10) {
                        makeCell(size2, 4.3f, "blue", layRight);
                        makeCell(size2, 14.7f, "blue", layRight);
                        makeCell(size2, 29.6f, "blue", layRight);
                        makeCell(size2, 70.4f, "blue", layRight);
                        makeCell(size2, 85.3f, "blue", layRight);
                        makeCell(size2, 95.7f, "blue", layRight);
                    } else if (size2 > setPoint10) {
                        makeCell(size2, 2.6f, "blue", layRight);
                        makeCell(size2, 8.2f, "blue", layRight);
                        makeCell(size2, 14.6f, "blue", layRight);
                        makeCell(size2, 22.6f, "blue", layRight);
                        makeCell(size2, 34.2f, "blue", layRight);
                        makeCell(size2, 65.8f, "blue", layRight);
                        makeCell(size2, 77.4f, "blue", layRight);
                        makeCell(size2, 85.4f, "blue", layRight);
                        makeCell(size2, 91.8f, "blue", layRight);
                        makeCell(size2, 97.4f, "blue", layRight);
                    }
                } else if ((size1 - size2) >= size2) {
                    long size0 = size1 - size2;
                    if (size0 <= setPoint6) {
                        makeCell(size0, 25.0f, "red", layLeft);
                        makeCell(size0, 75.0f, "red", layLeft);
                    } else if (size0 <= setPoint15) {
                        makeCell(size0, 16.7f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 83.3f, "red", layLeft);
                    } else if (size0 <= setPoint24) {
                        makeCell(size0, 12.5f, "red", layLeft);
                        makeCell(size0, 37.5f, "red", layLeft);
                        makeCell(size0, 62.5f, "red", layLeft);
                        makeCell(size0, 87.5f, "red", layLeft);
                    } else if (size0 <= setPoint35) {
                        makeCell(size0, 10.0f, "red", layLeft);
                        makeCell(size0, 30.0f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 70.0f, "red", layLeft);
                        makeCell(size0, 90.0f, "red", layLeft);
                    } else if (size0 <= setPoint48) {
                        makeCell(size0, 8.4f, "red", layLeft);
                        makeCell(size0, 25.1f, "red", layLeft);
                        makeCell(size0, 41.7f, "red", layLeft);
                        makeCell(size0, 58.3f, "red", layLeft);
                        makeCell(size0, 74.9f, "red", layLeft);
                        makeCell(size0, 91.6f, "red", layLeft);
                    } else if (size0 <= setPoint63) {
                        makeCell(size0, 7.2f, "red", layLeft);
                        makeCell(size0, 21.5f, "red", layLeft);
                        makeCell(size0, 35.7f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 64.3f, "red", layLeft);
                        makeCell(size0, 78.5f, "red", layLeft);
                        makeCell(size0, 92.8f, "red", layLeft);
                    } else if (size0 <= setPoint80) {
                        makeCell(size0, 6.25f, "red", layLeft);
                        makeCell(size0, 18.75f, "red", layLeft);
                        makeCell(size0, 31.25f, "red", layLeft);
                        makeCell(size0, 43.75f, "red", layLeft);
                        makeCell(size0, 56.25f, "red", layLeft);
                        makeCell(size0, 68.75f, "red", layLeft);
                        makeCell(size0, 81.25f, "red", layLeft);
                        makeCell(size0, 93.75f, "red", layLeft);
                    } else if (size0 <= setPoint99) {
                        makeCell(size0, 5.55f, "red", layLeft);
                        makeCell(size0, 16.65f, "red", layLeft);
                        makeCell(size0, 27.75f, "red", layLeft);
                        makeCell(size0, 38.85f, "red", layLeft);
                        makeCell(size0, 50.0f, "red", layLeft);
                        makeCell(size0, 61.15f, "red", layLeft);
                        makeCell(size0, 72.25f, "red", layLeft);
                        makeCell(size0, 83.35f, "red", layLeft);
                        makeCell(size0, 94.45f, "red", layLeft);
                    } else if (size0 > setPoint99) {
                        makeCell(size0, 5.0f, "red", layLeft);
                        makeCell(size0, 15.0f, "red", layLeft);
                        makeCell(size0, 25.0f, "red", layLeft);
                        makeCell(size0, 35.0f, "red", layLeft);
                        makeCell(size0, 45.0f, "red", layLeft);
                        makeCell(size0, 55.0f, "red", layLeft);
                        makeCell(size0, 65.0f, "red", layLeft);
                        makeCell(size0, 75.0f, "red", layLeft);
                        makeCell(size0, 85.0f, "red", layLeft);
                        makeCell(size0, 95.0f, "red", layLeft);
                    }
                    if (size2 <= setPoint6) {
                        makeCell(size2, 25.0f, "blue", layRight);
                        makeCell(size2, 75.0f, "blue", layRight);
                    } else if (size2 <= setPoint15) {
                        makeCell(size2, 16.7f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 83.3f, "blue", layRight);
                    } else if (size2 <= setPoint24) {
                        makeCell(size2, 12.5f, "blue", layRight);
                        makeCell(size2, 37.5f, "blue", layRight);
                        makeCell(size2, 62.5f, "blue", layRight);
                        makeCell(size2, 87.5f, "blue", layRight);
                    } else if (size2 <= setPoint35) {
                        makeCell(size2, 10.0f, "blue", layRight);
                        makeCell(size2, 30.0f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 70.0f, "blue", layRight);
                        makeCell(size2, 90.0f, "blue", layRight);
                    } else if (size2 <= setPoint48) {
                        makeCell(size2, 8.4f, "blue", layRight);
                        makeCell(size2, 25.1f, "blue", layRight);
                        makeCell(size2, 41.7f, "blue", layRight);
                        makeCell(size2, 58.3f, "blue", layRight);
                        makeCell(size2, 74.9f, "blue", layRight);
                        makeCell(size2, 91.6f, "blue", layRight);
                    } else if (size2 <= setPoint63) {
                        makeCell(size2, 7.2f, "blue", layRight);
                        makeCell(size2, 21.5f, "blue", layRight);
                        makeCell(size2, 35.7f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 64.3f, "blue", layRight);
                        makeCell(size2, 78.5f, "blue", layRight);
                        makeCell(size2, 92.8f, "blue", layRight);
                    } else if (size2 <= setPoint80) {
                        makeCell(size2, 6.25f, "blue", layRight);
                        makeCell(size2, 18.75f, "blue", layRight);
                        makeCell(size2, 31.25f, "blue", layRight);
                        makeCell(size2, 43.75f, "blue", layRight);
                        makeCell(size2, 56.25f, "blue", layRight);
                        makeCell(size2, 68.75f, "blue", layRight);
                        makeCell(size2, 81.25f, "blue", layRight);
                        makeCell(size2, 93.75f, "blue", layRight);
                    } else if (size2 <= setPoint99) {
                        makeCell(size2, 5.55f, "blue", layRight);
                        makeCell(size2, 16.65f, "blue", layRight);
                        makeCell(size2, 27.75f, "blue", layRight);
                        makeCell(size2, 38.85f, "blue", layRight);
                        makeCell(size2, 50.0f, "blue", layRight);
                        makeCell(size2, 61.15f, "blue", layRight);
                        makeCell(size2, 72.25f, "blue", layRight);
                        makeCell(size2, 83.35f, "blue", layRight);
                        makeCell(size2, 94.45f, "blue", layRight);
                    } else if (size2 > setPoint99) {
                        makeCell(size2, 5.0f, "blue", layRight);
                        makeCell(size2, 15.0f, "blue", layRight);
                        makeCell(size2, 25.0f, "blue", layRight);
                        makeCell(size2, 35.0f, "blue", layRight);
                        makeCell(size2, 45.0f, "blue", layRight);
                        makeCell(size2, 55.0f, "blue", layRight);
                        makeCell(size2, 65.0f, "blue", layRight);
                        makeCell(size2, 75.0f, "blue", layRight);
                        makeCell(size2, 85.0f, "blue", layRight);
                        makeCell(size2, 95.0f, "blue", layRight);
                    }

                }
            }
        }
    }



    private void makeCell(float size, float part, String back, LinearLayout lay) {

        LayoutParams paramsLayCell = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        paramsLayCell.weight = 1;

        RelativeLayout layCell = new RelativeLayout(getActivity());
//        layCell.setOrientation(LinearLayout.VERTICAL);

        layCell.setBackgroundColor(getResources().getColor(R.color.cWhite));
        switch (back) {
            case "red":
                layCell.setBackgroundColor(getResources().getColor(R.color.cLightRed));
                break;
            case "blue":
                layCell.setBackgroundColor(getResources().getColor(R.color.cLightBlue));
                break;
            case "green":
                layCell.setBackgroundColor(getResources().getColor(R.color.cLightGreen));
                break;
            default:
        }

        layCell.setLayoutParams(paramsLayCell);

        RelativeLayout.LayoutParams paramsLayCellBody = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramsLayCellBody.setMargins(8, 4, 8, 4);
        RelativeLayout layCellBody = new RelativeLayout(getActivity());
        layCellBody.setLayoutParams(paramsLayCellBody);

        TextView viewBody = new TextView(getActivity());
//        viewBody.setBackgroundColor(getResources().getColor(R.color.cWhite));
        viewBody.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);

        viewBody.setGravity(Gravity.CENTER);
        viewBody.setLayoutParams(paramsLayCell);

        switch(unitSizeCurrent) {
            case 0: // inches
                viewBody.setText(dg.convertNumbers(size * part / 25.4f / 100f));
            break;
            case 1: // mm
                viewBody.setText(String.valueOf(Math.round(size * part / 500f) * 5));
            break;
            default:
        }

        lay.addView(layCell);
        layCell.addView(layCellBody);
        layCellBody.addView(viewBody);
    }


    private void ShowBtnMethod(int method) {
        switch (method) {
            case 0:
                btnMethod.setText(getResources().getString(R.string.menuMethodLogT));
                break;
            case 1:
                btnMethod.setText(getResources().getString(R.string.menuMethodArea));
                break;
            default:
        }
    }
}
