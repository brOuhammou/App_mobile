package fr.imt_atlantique.myfirstapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Date birthdate;
    private TextView selectedBirthDate;
    boolean isDateSelected;

    public DatePickerFragment(TextView selectedBirthDate) {
        this.selectedBirthDate = selectedBirthDate;
        this.isDateSelected = false;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        if(isDateSelected)
            c.setTime(birthdate);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        birthdate = c.getTime();

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);
        birthdate = c.getTime();
        selectedBirthDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(birthdate));
        this.isDateSelected = true;
    }

    public Date getSelectedDate() {
        return this.birthdate;
    }

    public void clearDialog() {
        isDateSelected = false;
        birthdate = Calendar.getInstance().getTime();
    }
}
