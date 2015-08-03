module.exports = function(grunt) {

    // Project configuration.
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        uglify: {
            options: {
                banner: '/*! <%= pkg.name %> v<%= pkg.version %>, <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            build: {
                src: 'js/app.js',
                dest: 'build/js/app.min.js'
            }
        },
        concat: {
            options: {
            },
            dist: {
                src: [
                    'js/angular/**/*.js'
                ],
                dest: 'js/app.js'
            }
        },
        watch: {
            scripts: {
                files: ['Gruntfile.js', 'js/**/*.js', 'js/vendor/**/*.js'],
                tasks: ['concat'],
                options: {
                    debounceDelay: 250
                }
            }
        },
        jshint: {
            options: {
                browser: true,
                globals: {
                    jQuery: true
                }
            },
            all: {
                files: {
                    src: ['js/**/*.js']
                }
            }
        },
        bower: {
            install: {
                //just run 'grunt bower:install' and you'll see files from your Bower packages in lib directory
            }
        },
        cssmin: {
            dist: {
                options: {
                    banner: '/*! MyLib.js 1.0.0 | PackALunch (@PackALunch) | MIT Licensed */'
                },
                files: {
                    'build/css/style.min.css': ['css/**/*.css']
                }
            }
        }
    });


    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-cssmin');
    grunt.loadNpmTasks('grunt-contrib-concat');

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-jshint');

    grunt.loadNpmTasks('grunt-bower-task');

    // Default task(s).
    grunt.registerTask('default', ['concat','uglify', 'cssmin']);




};