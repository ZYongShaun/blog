/**
 * Created by shaun on 16/9/30.
 */

var gulp = require('gulp'),
    rename = require('gulp-rename'),
    clean = require('gulp-clean'),
    rev = require('gulp-rev'),
    revCollector = require('gulp-rev-collector'),
    watchPath = require('gulp-watch-path'),
    uglify = require('gulp-uglify'),
    minifycss = require('gulp-minify-css');

var watchCount = 1;

function copy(event) {
    var paths = watchPath(event, 'html/', './');
    // console.log(paths.srcPath + ", " + paths.distPath);
    gulp.src(paths.srcPath)
        .pipe(rename({
            extname: ""
        }))
        .pipe(gulp.dest('./'))
}

gulp.task('clean', function () {
    gulp.src(['js/index-*.js', 'js/lottery-*.js', 'css/style-*.css', './*.html'], {read: false})
        .pipe(clean());
});

gulp.task('javaScripts', function () {
    gulp.src(['src/js/index.js', 'src/js/lottery.js'])
        .pipe(rev())
        .pipe(uglify({
            mangle: true,
            compress: true
        }))
        // .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('js'))
        .pipe(rev.manifest({merge: true}))
        .pipe(rename({basename: 'js-manifest'}))
        .pipe(gulp.dest('src/manifest'));
});

gulp.task('styles', function () {
    gulp.src('src/css/style.css')
        .pipe(rev())
        .pipe(minifycss())
        // .pipe(rename({suffix: '.min'}))
        .pipe(gulp.dest('css'))
        .pipe(rev.manifest({merge: true}))
        .pipe(rename({basename: 'css-manifest'}))
        .pipe(gulp.dest('src/manifest'));
});

gulp.task('public', ['clean', 'build'], function () {
    return gulp.src(['src/manifest/*.json', 'src/*.html'])
        .pipe(revCollector({
            replaceReved: true
        }))
        .pipe(gulp.dest("./"));
});

gulp.task('build', ['javaScripts', 'styles']);

gulp.task('watch', function () {
    gulp.watch('html/*', function (event) {
        console.log("-----第" + watchCount + "次修改------");
        copy(event);
        watchCount++;
        console.log();
    });
});