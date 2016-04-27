package com.behindthemath.popularmoviesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lb.auto_fit_textview.AutoResizeTextView;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by aryeh on 3/21/2016.
 */
public class MovieDetailsFragment extends Fragment {
    private static final String ARGUMENT_MESSAGE = "message";
    @BindView(R.id.original_title) AutoResizeTextView originalTitle;
    @BindView(R.id.thumbnail) ImageView thumbnail;
    @BindView(R.id.release_year) TextView releaseYear;
    @BindView(R.id.vote_average) TextView voteAverage;
    @BindView(R.id.overview) TextView overview;
    private Unbinder unbinder;

    public MovieDetailsFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        unbinder = ButterKnife.bind(this, view);

        final Bundle args = getArguments();
        Movie movie = Parcels.unwrap(args.getParcelable(ARGUMENT_MESSAGE));

        /*
        AutoResizeTextView originalTitle = (AutoResizeTextView) view.findViewById(R.id.original_title);
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        TextView releaseYear = (TextView) view.findViewById(R.id.release_year);
        TextView voteAverage = (TextView) view.findViewById(R.id.vote_average);
        TextView overview = (TextView) view.findViewById(R.id.overview);
        */

        originalTitle.setText(movie.getOriginalTitle());
        originalTitle.setLines(1);

        Picasso.with(getContext()).load(movie.getThumbnailPath()).into(thumbnail);

        releaseYear.setText(movie.getReleaseYear());

        voteAverage.setText(movie.getVoteAverage().toString() + "/10");
        overview.setText(movie.getOverview());

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static MovieDetailsFragment newInstance(Movie movie) {
        final Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_MESSAGE, Parcels.wrap(movie));
        final MovieDetailsFragment fragment = new MovieDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
