<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Server extends CI_Controller {
	
	var $Key = '12345';
	
	public function __construct()
	{
		parent::__construct();
		$this->load->database();
		$this->load->library('input');
	}
	
	//***************************************************************************************************************

	function add_category()
	{

		if (!$this->input->post('u') || !$this->input->post('p')) {
			exit("-3");
		}
		
		$u = $this->input->post('u',TRUE);
		$p = md5($this->input->post('p',TRUE));
		$title = $this->input->post('title',TRUE);
		$parent = $this->input->post('parent',TRUE);
		
		if ($this->db->where('sUsername',$u)->where('sPassword',$p)->get('user')->num_rows() == 1) {
			$this->db->set('sTitle',$title);
			$this->db->set('sParent',$parent);
			$this->db->insert('category');
			exit(strval($this->db->insert_id()));
		}
		else
			exit('-1');
		
	}
	
	//***************************************************************************************************************

	function add_book()
	{

		if (!$this->input->post('u')		|| !$this->input->post('p')		|| !$this->input->post('title') || !$this->input->post('size') || 
			!$this->input->post('parent')	|| !$this->input->post('author') || !$this->input->post('desc') || 
			!$this->input->post('lang')		|| !$this->input->post('cover')) {
			exit("-3");
		}
		
		$u		= $this->input->post('u',TRUE);
		$p		= md5($this->input->post('p',TRUE));
		$title	= $this->input->post('title',TRUE);
		$parent	= $this->input->post('parent',TRUE);
		$size	= $this->input->post('size',TRUE);
		$author = $this->input->post('author',TRUE);
		$dessc	= $this->input->post('desc',TRUE);
		$lang	= $this->input->post('lang',TRUE);
		$cover	= $this->input->post('cover',TRUE);
		$isedit = $this->input->post('isedit',TRUE);
		$id		= $this->input->post('id',TRUE);
		
		if ($this->db->where('sUsername',$u)->where('sPassword',$p)->get('user')->num_rows() == 1) {
		
			$this->db->set('sTitle',$title);
			$this->db->set('sCategoryID',$parent);
			$this->db->set('sAuthor',$author);
			$this->db->set('sRate','1');
			$this->db->set('sDescription',$dessc);
			$this->db->set('sFileSize',$size);
			$this->db->set('sLanguage',$lang);
			$this->db->set('sDownload','1');
			
			if ($isedit != '1') {
				$this->db->insert('book');
				$insert_id = $this->db->insert_id();
				$this->db->set('sCover','books/cover/cover_'.$insert_id.'.jpg')->where('sID',$insert_id)->update('book');
				exit(strval($insert_id));
			} else {
				$this->db->where('sID',$id);
				$this->db->update('book');
				exit(strval($id));
			}
			
		}
		
		else
			exit('-4');
	}
	
	//***************************************************************************************************************

	function get_message()
	{
		echo json_encode($this->db->order_by('sDateTime','DESC')->get('messages')->result_array());
	}
	
	//***************************************************************************************************************

	function get_message_points($id)
	{
		if (filter_var($id,FILTER_VALIDATE_INT))
			echo json_encode($this->db->where('sNewsID',$id)->get('points')->result_array());
		else
			echo json_encode(array());
	}
	
	//***************************************************************************************************************
	
	function add_points() {
		if ($this->input->post('name') && $this->input->post('comment') && $this->input->post('device') && 
			$this->input->post('comment') && $this->input->post('id'))
			{
				$name = $this->input->post('name',TRUE);
				$comment = $this->input->post('comment',TRUE);
				$device = $this->input->post('device',TRUE);
				$id = $this->input->post('id',TRUE);
				
				$this->db->set('sName',$name);
				$this->db->set('sComment',$comment);
				$this->db->set('sDeviceType',$device);
				$this->db->set('sNewsID',$id);
				$this->db->insert('points');
				echo '1';
			}
		else
			echo '0';
	}
	
	//***************************************************************************************************************

	function contact()
	{
		
		if (!$this->input->post('name') || !$this->input->post('email') || !$this->input->post('email')) {
			exit("-1");
		}
		
		$name = $this->input->post('name',TRUE);
		$mail = $this->input->post('email',TRUE);
		$comment = $this->input->post('comment',TRUE);
		
		$this->db->set('sName',$name);
		$this->db->set('sEmail',$mail);
		$this->db->set('sComment',$comment);
		$this->db->insert('contact');
		exit('1');
		
	}
	
	//***************************************************************************************************************

	function login()
	{
		
		if (!$this->input->post('u') || !$this->input->post('p')) {
			exit("-1");
		}
		
		$u = $this->input->post('u',TRUE);
		$p = md5($this->input->post('p',TRUE));
		
		if ($this->db->where('sUsername',$u)->where('sPassword',$p)->get('user')->num_rows() == 1) {
			$messages = $this->db->where('sVerify','0')->order_by('sDateTime','DESC')->get('contact')->result_array();
			$links    = $this->db->get('links')->result_array();
			$cat	  = $this->db->get('category')->result_array();
			$this->db->update('contact',array('sVerify'=>'1'),array('sVerify'=>'0'));
			exit(json_encode(array('message' => $messages , 'result' => $links , 'cat' => $cat)));
		}
			
		else
			exit('-1');
		
	}
	
	
	//***************************************************************************************************************

	function get_books()
	{
		
		if (!$this->input->post('id')) {
			exit("-1");
		}
		
		$id = $this->input->post('id',TRUE);
		exit(json_encode(array('result' => $this->db->where('sCategoryID',$id)->order_by('sPublishDate','DESC')->get('book')->result_array())));
		
	}
	
	//***************************************************************************************************************

	function get_link($id)
	{
		echo json_encode($this->db->get('link')->result_array());
	}
	
	//***************************************************************************************************************

	function addlink()
	{
		
		if (!$this->input->post('title') || !$this->input->post('link1') || !$this->input->post('link2') || !$this->input->post('type') || !$this->input->post('u') || !$this->input->post('p')) {
			exit("-3");
		}
		
		$title = $this->input->post('title',TRUE);
		$link1 = $this->input->post('link1',TRUE);
		$link2 = $this->input->post('link2',TRUE);
		$type  = $this->input->post('type',TRUE);
		$u = $this->input->post('u',TRUE);
		$p = md5($this->input->post('p',TRUE));
		
		if ($this->db->where('sUsername',$u)->where('sPassword',$p)->get('user')->num_rows() == 1)
			{
				$this->db->set('sTitle',$title);
				$this->db->set('sLink1',$link1);
				$this->db->set('sLink2',$link2);
				$this->db->set('sType',$type);
				$this->db->insert('links');
				exit('1');
			}
		else
			exit('-2');
		
	}
	
	//***************************************************************************************************************

	function deletelink()
	{
		
		if (!$this->input->post('id') || !$this->input->post('u') || !$this->input->post('p')) {
			exit("-3");
		}
		
		$id = $this->input->post('id',TRUE);
		$u = $this->input->post('u',TRUE);
		$p = md5($this->input->post('p',TRUE));
		
		if ($this->db->where('sUsername',$u)->where('sPassword',$p)->get('user')->num_rows() == 1)
			{
				$this->db->where('sID',$id);
				$this->db->delete('links');
				exit('1');
			}
		else
			exit('-2');
		
	}
	
	//***************************************************************************************************************
	
	function remove_category()
	{
		
		if (!$this->input->post('id') || !$this->input->post('u') || !$this->input->post('p')) {
			exit("-3");
		}
		
		$id = $this->input->post('id',TRUE);
		$u = $this->input->post('u',TRUE);
		$p = md5($this->input->post('p',TRUE));
		
		if ($this->db->where('sUsername',$u)->where('sPassword',$p)->get('user')->num_rows() == 1)
			{
				$this->db->where('sID',$id)->delete('category');
				$books = $this->db->where('sCategoryID',$id)->get('book');
				if ($books->num_rows() > 0) {
					foreach($books->result_array() as $row) {
						$this->remove_book($row['sID'],$u,$p);
					}
				}
				exit('1');
			}
		else
			exit('-2');
	}
	
	function remove_book($id,$u,$p)
	{
		
		if ($this->db->where('sUsername',$u)->where('sPassword',md5($p))->get('user')->num_rows() == 1)
			{
				$res = $this->db->where('sID',$id)->get('book');
				
				if ($res->num_rows() == 1) {
					$row = $res->row();
					@unlink($row->sCover);
					@unlink('books/book_'.$row->sID.'.zip');
					$this->db->where('sID',$id)->delete('book');
					echo '1';
				}
				else
					echo '-1';
			}
		else
			echo '-2';
	}
	
	//***************************************************************************************************************
	
	function list_category()
	{
		
		$sql = 'SELECT *,(SELECT COUNT(*) FROM tbl_book WHERE sCategoryID = tbl_category.sID) AS sCount FROM tbl_category';
		
		$res = $this->db->query($sql);
		
		if ($res->num_rows() == 0) {
			exit(json_encode(array('success' => FALSE,'error' => 'چنین دسته بندی وجود ندارد')));
		}
		
		else {
			exit(json_encode(array('success' => TRUE , 'result' => $res->result_array())));
		}
		
	}
	
	//***************************************************************************************************************
	
	function list_book()
	{
			
		$res = $this->db->order_by('sPublishDate','DESC')->get('book');
		
		if ($res->num_rows() == 0) {
			exit(json_encode(array('success' => FALSE,'error' => 'هیچ کتابی وجود ندارد')));
		}
		
		else {
			exit(json_encode(array('success' => TRUE , 'result' => $res->result_array())));
		}
		
	}
	
	//***************************************************************************************************************
		
	function count_book()
		{
				
			$res = $this->db->get('book');
			exit(json_encode(array('success' => TRUE,'result' => $res->num_rows())));
			
		}
		
	//***************************************************************************************************************
		
	function top_book()
		{
			$this->db->where('sDownload >','10',TRUE);
			$res = $this->db->get('book');
			
			if ($res->num_rows() == 0) {
				exit(json_encode(array('success' => FALSE,'error' => 'هیچ کتابی وجود ندارد')));
			}
			
			else {
				exit(json_encode(array('success' => TRUE , 'result' => $res->result_array())));
			}
			
		}
		
	//***************************************************************************************************************
		
	function search_book($str)
		{
			
			$this->db->where('sTitle',$str);
			$this->db->or_where('sDescription',$str);
			$res = $this->db->get('book');
			
			if ($res->num_rows() == 0) {
				exit(json_encode(array('success' => FALSE,'error' => 'هیچ کتابی وجود ندارد')));
			}
			
			else {
				exit(json_encode(array('success' => TRUE , 'result' => $res->result_array())));
			}
			
		}
		
	//***************************************************************************************************************
	
	function add_download($id)
	{
		
		$this->db->where('sID',$id);
		$res = $this->db->get('book');
		
		if ($res->num_rows() != 0) {
			$dc = $res->row()->sDownload;
			++$dc;
			$this->db->where('sID',$id)->update('book',array('sDownload'=>$dc));
		}
		
		exit(json_encode(array('success' => TRUE)));
		
	}
	
}

?>