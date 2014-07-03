		<div id="contenedore">
			<footer id="mainfooter">
				<p class="copyright">Copyright &copy, 2014 <a href="#">CSN news</a> Todos los derechos reservados</p>										
			</footer>
		</div>
	<script type="text/javascript">
$(function() {
 
    var menu_ul = $('.leftmenu > li > ul'),
        menu_a  = $('.leftmenu > li > a');
     
    menu_ul.hide();
 
    menu_a.click(function(e) {
        e.preventDefault();
        if(!$(this).hasClass('active')) {
            menu_a.removeClass('active');
            menu_ul.filter(':visible').slideUp('normal');
            $(this).addClass('active').next().stop(true,true).slideDown('normal');
        } else {
            $(this).removeClass('active');
            $(this).next().stop(true,true).slideUp('normal');
        }
    });
 
});
</script>
</div>	
</body>
</html>
